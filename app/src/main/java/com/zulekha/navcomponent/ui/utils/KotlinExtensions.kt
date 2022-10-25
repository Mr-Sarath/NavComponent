package com.zulekha.navcomponent.ui.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
 import com.google.android.material.snackbar.Snackbar


fun logThis(message: Any?) {
    Log.d("logThis","-->  $message")
}

fun Context.longToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.shortToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.shortToast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.appBackSnack(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(this, message, length)
    snack.setActionTextColor(Color.parseColor("#FFFFFF"))
    snack.view.setBackgroundColor(Color.parseColor("#51C989"))
    snack.show()
}





