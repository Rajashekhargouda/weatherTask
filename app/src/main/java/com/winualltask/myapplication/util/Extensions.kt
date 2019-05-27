package com.winualltask.myapplication.util

import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJsonString(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

