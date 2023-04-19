package com.hasanalic.rickandmorty.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPreferences {

    companion object {
        private val PREFERENCE_GREETING = "greeting"
        private var sharedPreferences: SharedPreferences? = null
        @Volatile private var instance: CustomSharedPreferences? = null

        operator fun invoke(context: Context): CustomSharedPreferences = instance ?: synchronized(Any()) {
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return  CustomSharedPreferences()
        }
    }

    fun setMessage(msg: String) {
        sharedPreferences?.edit(commit = true) {
            putString(PREFERENCE_GREETING, msg)
        }
    }

    fun getMessage() = sharedPreferences?.getString(PREFERENCE_GREETING,"Welcome!")
}