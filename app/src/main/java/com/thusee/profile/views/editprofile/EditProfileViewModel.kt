package com.thusee.profile.views.editprofile

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thusee.profile.data.request.Cities
import com.thusee.profile.data.request.UpdateProfileRequest
import com.thusee.profile.data.response.KeyValue
import com.thusee.profile.data.response.Locations
import com.thusee.profile.usecase.FetchMultiChoiceRepo
import com.thusee.profile.usecase.UpdateProfileRepo
import com.thusee.profile.util.AppUtils.readFromAsset
import com.thusee.profile.views.editprofile.event.EditProfileUiViewState
import com.thusee.profile.views.editprofile.event.MultiChoiceLoadEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class EditProfileViewModel(
    private val context: Context,
    private val fetchMultiChoiceRepo: FetchMultiChoiceRepo,
    private val updateProfileRepo: UpdateProfileRepo,
): ViewModel(),
    KoinComponent {

    val viewState = MutableLiveData<EditProfileUiViewState>()
    var multiChoiceLiveData = MutableLiveData<MultiChoiceLoadEvent>()

    private var data: MutableMap<String, List<KeyValue>> = mutableMapOf()
    private var cities: MutableList<Cities> = mutableListOf()

    init {
        fetchMultiChoiceData()
    }

    private fun fetchMultiChoiceData() {
        viewModelScope.launch {
            fetchMultiChoiceRepo.getMultiChoiceData().catch { ex ->

            }.collect {
                data = it
                multiChoiceLiveData.value = MultiChoiceLoadEvent.LoadMultiChoiceData(it, fetchCities(context))
            }
        }
    }

    fun getMultiChoiceData(): MutableMap<String, List<KeyValue>> {
        return data.toMutableMap()
    }
    fun getCities(): MutableList<Cities> {
        return cities
    }

    fun updateProfile(updateRequest: UpdateProfileRequest) {
        viewModelScope.launch {
            updateProfileRepo.updateProfile(updateRequest).onStart {
                viewState.value = EditProfileUiViewState.ShowProgressBar
            }.catch { ex ->
                viewState.value = EditProfileUiViewState.HideProgressBar
                viewState.value = EditProfileUiViewState.ErrorHandle(ex)
            }.collect {
                viewState.value = EditProfileUiViewState.HideProgressBar
                viewState.value = EditProfileUiViewState.Response(it)
            }
        }
    }

    private fun fetchCities(context: Context): List<Cities> {
        val cityStrings = readFromAsset(context, "cities.json")
        cities = Gson().fromJson(cityStrings, Locations::class.java).cities.toMutableList()
        return cities
    }

}