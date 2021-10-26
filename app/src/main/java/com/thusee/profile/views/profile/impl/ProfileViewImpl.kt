package com.thusee.profile.views.profile.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.thusee.profile.R
import com.thusee.profile.data.response.Data
import com.thusee.profile.databinding.ActivityProfileBinding
import com.thusee.profile.util.handleError
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
            is ProfileView.UiState.ShowProgressBar -> showProgress()
            is ProfileView.UiState.HideProgressBar -> hideProgress()
            is ProfileView.UiState.ErrorHandle -> showError(state.e)
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

        Glide.with(binding.root.context)
            .load(data.profilePic)
            .fitCenter()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(binding.profilePicture)
    }

    private fun showProgress() {
        binding.rowLoadingAnim.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.rowLoadingAnim.visibility = View.GONE
    }

    private fun showError(error: Throwable) {
        Toast.makeText(
            binding.root.context,
            binding.root.context.handleError(error),
            Toast.LENGTH_SHORT
        ).show()

    }
}