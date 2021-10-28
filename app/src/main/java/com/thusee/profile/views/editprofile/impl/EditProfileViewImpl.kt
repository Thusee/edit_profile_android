package com.thusee.profile.views.editprofile.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.thusee.profile.R
import com.thusee.profile.data.request.Cities
import com.thusee.profile.data.request.UpdateProfileRequest
import com.thusee.profile.data.response.Data
import com.thusee.profile.data.response.KeyValue
import com.thusee.profile.databinding.ActivityEditProfileBinding
import com.thusee.profile.util.AppUtils.getArrayAdapter
import com.thusee.profile.util.AppUtils.getCityObjectFromList
import com.thusee.profile.util.AppUtils.getCityStringList
import com.thusee.profile.util.AppUtils.getIdFromMultiChoiceList
import com.thusee.profile.util.ETHNICITY
import com.thusee.profile.util.FIGURE
import com.thusee.profile.util.GENDER
import com.thusee.profile.util.MARITAL_STATUS
import com.thusee.profile.util.RELIGION
import com.thusee.profile.util.hideProgressBar
import com.thusee.profile.util.loadImage
import com.thusee.profile.util.showError
import com.thusee.profile.util.showProgressBar
import com.thusee.profile.util.showToast
import com.thusee.profile.views.editprofile.EditProfileView
import com.thusee.profile.views.editprofile.SubmitButtonClickListener
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import timber.log.Timber


class EditProfileViewImpl: EditProfileView {

    lateinit var binding: ActivityEditProfileBinding

    override fun inflate(inflater: LayoutInflater, savedInstanceState: Bundle?): View {
        binding = ActivityEditProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun changeState(state: EditProfileView.State) {
        when (state) {
            is EditProfileView.State.DisplayMultiChoiceData -> updateMultiChoiceUI(
                state.data,
                state.cities
            )
        }
    }

    override fun updateUI(data: Data) {

        binding.editProfilePicture.loadImage(data.profilePic)

        binding.editDisplayNameText.setText(data.displayName)
        binding.editRealNameText.setText(data.realName)
        binding.editGenderText.setText(data.gender)
        binding.editEthnicityText.setText(data.ethnicity)
        binding.editReligionText.setText(data.religion)
        binding.editHeightText.setText("${data.height}")
        binding.editFigureText.setText(data.figure)
        binding.editMaritalStatusText.setText(data.maritalStatus)
        binding.editOccupationText.setText(data.occuapion)
        binding.editAboutMeText.setText(data.aboutMe)
        binding.editLocationText.setText(data.location?.city)
    }

    override fun getUpdateProfileRequest(result: MutableMap<String, List<KeyValue>>, cities: List<Cities>): UpdateProfileRequest {

        val updateRequest = UpdateProfileRequest()
        updateRequest.profilePic =
            "https://firebasestorage.googleapis.com/v0/b/testing-23da3.appspot.com/o/steve-jobs-1.jpg?alt=media&token=2a81186e-3143-425a-8f00-9331c81e3955"
        updateRequest.displayName = binding.editDisplayNameText.text.toString()
        updateRequest.realName = binding.editRealNameText.text.toString()
        updateRequest.birthday = "1987-03-20"
        updateRequest.gender =
            getIdFromMultiChoiceList(result, GENDER, binding.editGenderText.text.toString()).trim()
        updateRequest.ethnicity =
            getIdFromMultiChoiceList(
                result,
                ETHNICITY,
                binding.editEthnicityText.text.toString()
            ).trim()
        updateRequest.religion =
            getIdFromMultiChoiceList(
                result,
                RELIGION,
                binding.editReligionText.text.toString()
            ).trim()
        updateRequest.figure =
            getIdFromMultiChoiceList(result, FIGURE, binding.editFigureText.text.toString()).trim()
        updateRequest.maritalStatus = getIdFromMultiChoiceList(
            result,
            MARITAL_STATUS,
            binding.editMaritalStatusText.text.toString().trim()
        )
        updateRequest.height = binding.editHeightText.text.toString().toInt()
        updateRequest.occuapion = binding.editOccupationText.text.toString()
        updateRequest.aboutMe = binding.editAboutMeText.text.toString()
        updateRequest.location = getCityObjectFromList(cities, binding.editLocationText.text.toString())



        Timber.d("UpdateRequest : $updateRequest")
        return updateRequest

    }

    override fun setCallBack(listener: SubmitButtonClickListener) {
        binding.submit.setOnClickListener(listener)
    }

    override fun showPopUpDialog() {
        TODO("Not yet implemented")
    }

    override fun validateTextField(): Boolean {
        val displayName = binding.editDisplayNameText.text.toString()
        val realName = binding.editRealNameText.text.toString()
        val maritalStatus = binding.editMaritalStatusText.text.toString()
        val gender = binding.editGenderText.text.toString()
        val location = binding.editLocationText.text.toString()

        when {
            displayName.isEmpty() -> {
                binding.root.showToast("Please enter the display name")
                return false
            }
            realName.isEmpty() -> {
                binding.root.showToast("Please enter the real name")
                return false
            }
            maritalStatus.isEmpty() -> {
                binding.root.showToast("Please select the marital status")
                return false
            }
            gender.isEmpty() -> {
                binding.root.showToast("Please select the gender")
                return false
            }
            location.isEmpty() -> {
                binding.root.showToast("Please select location")
                return false
            }
            else -> return true
        }

    }

    override fun changeUiState(state: EditProfileView.UiState) {
        when (state) {
            is EditProfileView.UiState.ShowProgressBar -> binding.rowLoadingAnim.showProgressBar()
            is EditProfileView.UiState.HideProgressBar -> binding.rowLoadingAnim.hideProgressBar()
            is EditProfileView.UiState.ErrorHandle -> binding.root.showError(state.e)
            is EditProfileView.UiState.Response -> binding.root.showToast(state.response.message)
        }
    }

    private fun updateMultiChoiceUI(
        result: MutableMap<String, List<KeyValue>>,
        cities: List<Cities>
    ) {

        (binding.editEthnicity.editEthnicityText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, ETHNICITY)
        )
        (binding.editGender.editGenderText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, GENDER)
        )
        (binding.editFigure.editFigureText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, FIGURE)
        )
        (binding.editMaritalStatus.editMaritalStatusText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, MARITAL_STATUS)
        )
        (binding.editReligion.editReligionText as? AutoCompleteTextView)?.setAdapter(
            getArrayAdapter(result, binding.root.context, RELIGION)
        )

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(
                binding.root.context,
                R.layout.list_item,
                getCityStringList(cities)
            )

        (binding.editLocation.editLocationText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}