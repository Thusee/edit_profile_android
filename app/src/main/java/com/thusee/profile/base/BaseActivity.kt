package com.thusee.profile.base

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.thusee.profile.R
import com.thusee.profile.util.handleError
import com.thusee.profile.viewstate.UiViewState
import kotlinx.android.synthetic.main.activity_profile.*

open class BaseActivity : AppCompatActivity() {

    fun showToolBarBackButton() {
        val backButton = findViewById<AppCompatImageView>(R.id.back)
        backButton.visibility = View.VISIBLE
        backButton.setOnClickListener { onBackPressed() }

    }

    fun hideToolBarBackButton() {
        val backButton = findViewById<AppCompatImageView>(R.id.back)
        backButton.visibility = View.GONE
    }
}