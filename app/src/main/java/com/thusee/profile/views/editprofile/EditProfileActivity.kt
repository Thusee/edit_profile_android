package com.thusee.profile.views.editprofile

import android.os.Bundle
import com.thusee.profile.base.BaseActivity
import com.thusee.profile.data.response.Data
import com.thusee.profile.util.PROFILE_DATA_KEY
import com.thusee.profile.views.editprofile.observer.EditProfileViewObserver
import com.thusee.profile.views.editprofile.observer.MultiChoiceObserver
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class EditProfileActivity: BaseActivity() {

    private val viewModel: EditProfileViewModel by viewModel()
    private val rootView: EditProfileView by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView.inflate(layoutInflater, savedInstanceState))

        showToolBarBackButton()
        setCallBack()

        val profileData = intent.getParcelableExtra(PROFILE_DATA_KEY) ?: Data()
        rootView.updateUI(profileData)

        setupObservers()

    }

    private fun setCallBack(){
        rootView.setCallBack(SubmitButtonClickListener(viewModel, rootView))
    }

    private fun setupObservers() {
        viewModel.multiChoiceLiveData.observe(this, MultiChoiceObserver(rootView))
        viewModel.viewState.observe(this, EditProfileViewObserver(rootView))
    }

}