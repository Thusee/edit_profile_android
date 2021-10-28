package com.thusee.profile.views.editprofile

import android.view.View
import com.thusee.profile.R

class SubmitButtonClickListener(
    private val viewModel: EditProfileViewModel,
    private val rootView: EditProfileView
): View.OnClickListener {

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.submit -> {
                if (rootView.validateTextField()) {
                    viewModel.getMultiChoiceData().let {
                        rootView.getUpdateProfileRequest(it, viewModel.getCities())
                    }.let { request ->
                        viewModel.updateProfile(request)
                    }
                }
            }
        }

    }
}