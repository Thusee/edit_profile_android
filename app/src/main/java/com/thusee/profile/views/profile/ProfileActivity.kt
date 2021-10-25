package com.thusee.profile.views.profile

import android.os.Bundle
import com.thusee.profile.base.BaseActivity
import com.thusee.profile.views.profile.observer.ProfileLoadDataObserver
import com.thusee.profile.views.profile.observer.UiViewObserver
import org.koin.android.ext.android.inject

class ProfileActivity: BaseActivity() {

    private val rootView: ProfileView by inject()
    private val viewModel: ProfileViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView.inflate(this, savedInstanceState))

        hideToolBarBackButton()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(this, UiViewObserver(rootView))
        viewModel.profileLiveData.observe(this, ProfileLoadDataObserver(rootView))
    }
}