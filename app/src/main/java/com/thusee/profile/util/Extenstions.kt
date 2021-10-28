package com.thusee.profile.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.thusee.profile.R
import retrofit2.HttpException
import java.net.UnknownHostException

fun Context.handleError(e: Throwable?): String {
    if (e is HttpException) {
        val body = e.response()?.errorBody()
        val errorMessage = body?.string()

        if (errorMessage!!.isNotEmpty()) {
            // Need to throw the error msg according to the API Response then,
            // I only know the 401 code else pass the errorMessage from the API
            return when (e.code()) {
                401 -> {
                    getString(R.string.unauthorized_ex)
                }
                else -> {
                    errorMessage
                }
            }
        }
        return getString(R.string.failed_error)
    } else if (e is UnknownHostException) {
        return getString(R.string.network_not_available)
    }

    return getString(R.string.failed_error)
}

fun ImageView.loadImage(url: String) {

    Glide.with(this)
        .load(url)
        .fitCenter()
        .placeholder(R.mipmap.ic_launcher_round)
        .into(this)
}

fun View.showProgressBar(){
    this.visibility = View.VISIBLE
}
fun View.hideProgressBar(){
    this.visibility = View.GONE
}

fun View.showError(error: Throwable) {
    Toast.makeText(
        this.context,
        this.context.handleError(error),
        Toast.LENGTH_SHORT
    ).show()
}

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}
