package com.thusee.profile.views.editprofile

import android.os.Bundle
import android.widget.Toast
import com.thusee.profile.R
import com.thusee.profile.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class EditProfileActivity: BaseActivity() {

    private val viewModel: EditProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        showToolBarBackButton()

        viewModel.multiChoiceLiveData.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        } )

    }
}