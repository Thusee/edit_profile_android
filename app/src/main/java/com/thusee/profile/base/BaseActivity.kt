package com.thusee.profile.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.thusee.profile.R

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