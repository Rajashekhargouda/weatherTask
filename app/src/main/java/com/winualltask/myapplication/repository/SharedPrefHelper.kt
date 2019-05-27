package com.winualltask.myapplication.repository

import android.content.Context

class SharedPrefHelper {

    companion object {
        private const val PREFERENCE_NAME = "weatherSharePref"
        private const val SHARED_KEY_LOG = "lastLog"

        fun storeLogInPref(context: Context,logTime:Long ){
            val sharedPrefEditor = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE).edit()
            sharedPrefEditor
                .putLong(SHARED_KEY_LOG,logTime)
                .apply()
        }

        fun getLogFromPref(context: Context):Long{
            val sharedPref = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
            return sharedPref.getLong(SHARED_KEY_LOG,-1)
        }
    }

}