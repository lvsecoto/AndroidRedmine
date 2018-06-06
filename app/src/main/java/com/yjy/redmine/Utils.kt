package com.yjy.redmine

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun Context.showToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: CharSequence) {
    activity!!.showToast(message)
}
