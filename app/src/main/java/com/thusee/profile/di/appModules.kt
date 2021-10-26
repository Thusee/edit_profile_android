package com.thusee.profile.di

import com.thusee.profile.BuildConfig
import com.thusee.profile.data.network.ApiService
import com.thusee.profile.usecase.FetchMultiChoiceRepo
import com.thusee.profile.views.profile.ProfileView
import com.thusee.profile.views.profile.impl.ProfileViewImpl
import com.thusee.profile.views.profile.ProfileViewModel
import com.thusee.profile.usecase.FetchProfileRepo
import com.thusee.profile.views.editprofile.EditProfileViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModules = module{

    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }

    factory { FetchProfileRepo(get()) }
    factory { FetchMultiChoiceRepo(get()) }

    single<ProfileView> { ProfileViewImpl() }

    single<ApiService> {
        val okHttpClient = OkHttpClient()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}