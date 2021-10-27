package com.thusee.profile.views.profile

import android.content.Intent
import android.view.View
import com.thusee.profile.R
import com.thusee.profile.util.PROFILE_DATA_KEY
import com.thusee.profile.views.editprofile.EditProfileActivity

class ProfileOnClickListener(private val viewModel: ProfileViewModel): View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.editButton -> {
                val intent = Intent(v.context, EditProfileActivity::class.java)
                intent.putExtra(PROFILE_DATA_KEY, viewModel.getProfileData())
                v.context.startActivity(intent)
            }
        }
    }
}