package com.thusee.profile.views.editprofile

import android.os.Bundle
import com.thusee.profile.R
import com.thusee.profile.base.BaseActivity

class EditProfileActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        showToolBarBackButton()

    }
}