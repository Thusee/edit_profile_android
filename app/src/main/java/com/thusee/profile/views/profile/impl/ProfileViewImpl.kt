package com.thusee.profile.views.profile.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.thusee.profile.data.response.Data
import com.thusee.profile.databinding.ActivityProfileBinding
import com.thusee.profile.util.handleError
import com.thusee.profile.util.hideProgressBar
import com.thusee.profile.util.loadImage
import com.thusee.profile.util.showError
import com.thusee.profile.util.showProgressBar
import com.thusee.profile.views.profile.ProfileOnClickListener
import com.thusee.profile.views.profile.ProfileView

class ProfileViewImpl: ProfileView {

    lateinit var binding: ActivityProfileBinding

    override fun inflate(inflater: LayoutInflater, savedInstanceState: Bundle?): View? {
        binding = ActivityProfileBinding.inflate(inflater, null, false)
        return binding.root
    }

    override fun changeState(state: ProfileView.State) {
        when (state) {
            is ProfileView.State.DisplayProfileData -> updateProfile(state.data)
        }
    }

    override fun changeUiState(state: ProfileView.UiState) {
        when (state) {
            is ProfileView.UiState.ShowProgressBar -> binding.rowLoadingAnim.showProgressBar()
            is ProfileView.UiState.HideProgressBar -> binding.rowLoadingAnim.hideProgressBar()
            is ProfileView.UiState.ErrorHandle -> binding.root.showError(state.e)
        }
    }

    override fun setCallBack(listener: ProfileOnClickListener) {
        binding.editButton.setOnClickListener(listener)
    }

    private fun updateProfile(data: Data) {
        binding.displayNameText.text = data.displayName
        binding.birthdayText.text = data.birthday
        binding.genderText.text = data.gender
        binding.ethnicityText.text = data.ethnicity
        binding.religionText.text = data.religion
        binding.heightText.text = "${data.height}"
        binding.figureText.text = data.figure
        binding.aboutMeText.text = data.aboutMe
        binding.locationText.text = data.location?.city

        binding.profilePicture.loadImage(data.profilePic)
    }

}