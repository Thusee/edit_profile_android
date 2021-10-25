package com.thusee.profile.views.profile

import android.view.View
import com.thusee.profile.R

class ProfileOnClickListener(private val profileView: ProfileView): View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.editButton -> {
                profileView.navigateToEditProfile()
            }
        }
    }
}