package com.thusee.profile.views.profile

import android.os.Bundle
import com.thusee.profile.base.BaseActivity
import com.thusee.profile.views.profile.observer.ProfileLoadDataObserver
import com.thusee.profile.views.profile.observer.UiViewObserver
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileActivity: BaseActivity() {

    private val rootView: ProfileView by inject()
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView.inflate(layoutInflater, savedInstanceState))

        hideToolBarBackButton()
        setupObservers()
        setCallBack()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchProfileData()
    }

    private fun setCallBack(){
        rootView.setCallBack(ProfileOnClickListener(viewModel))
    }


    private fun setupObservers() {
        viewModel.viewState.observe(this, UiViewObserver(rootView))
        viewModel.profileLiveData.observe(this, ProfileLoadDataObserver(rootView))
    }
}