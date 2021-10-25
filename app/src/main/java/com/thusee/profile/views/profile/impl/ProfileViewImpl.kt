package com.thusee.profile.views.profile.impl

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.thusee.profile.R
import com.thusee.profile.data.response.Data
import com.thusee.profile.util.handleError
import com.thusee.profile.views.profile.ProfileOnClickListener
import com.thusee.profile.views.profile.ProfileView
import kotlinx.android.synthetic.main.activity_profile.view.*

class ProfileViewImpl: ProfileView {

    private var rootView: View? = null

    override fun inflate(activity: Activity, savedInstanceState: Bundle?): View? {
        rootView = activity.layoutInflater.inflate(R.layout.activity_profile, null, false)
        return rootView
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
        rootView?.apply {
            editButton.setOnClickListener(listener)
        }
    }

    private fun updateProfile(data: Data) {
        rootView?.apply {
            displayNameText.text = data.displayName
            birthdayText.text = data.birthday
            genderText.text = data.gender
            ethnicityText.text = data.ethnicity
            religionText.text = data.religion
            heightText.text = "${data.height}"
            figureText.text = data.figure
            aboutMeText.text = data.aboutMe
            locationText.text = data.location?.city

            Glide.with(rootView.context)
                .load(data.profilePic)
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(profilePicture)
        }
    }

    private fun showProgress() {
        rootView?.apply {
            rowLoadingAnim.visibility = View.VISIBLE
        }
    }

    private fun hideProgress() {
        rootView?.apply {
            rowLoadingAnim.visibility = View.GONE
        }
    }

    private fun showError(error: Throwable) {
        rootView?.apply {
            Toast.makeText(rootView.context, context.handleError(error), Toast.LENGTH_SHORT).show()

        }
    }

}