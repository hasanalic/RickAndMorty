package com.hasanalic.rickandmorty.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPreferences {

    companion object {
        private val PREFERENCE_GREETING = "greeting"
        private val PREFERENCE_CONTROL = "control"
        private val PREFERENCE_PAGE_COUNT = "page"
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

    fun setControl(control: Boolean) {
        sharedPreferences?.edit(commit = true) {
            putBoolean(PREFERENCE_CONTROL,control)
        }
    }

    fun setPageCount() {
        sharedPreferences?.edit(commit = true) {
            putInt(PREFERENCE_PAGE_COUNT,1)
        }
    }

    fun getNextPage(): Int {
        var pageCount = sharedPreferences?.getInt(PREFERENCE_PAGE_COUNT, 1)
        if (pageCount != null && pageCount != -1) {
            if ((pageCount + 1) < 8) {
                pageCount++
            } else {
                pageCount = -1
            }
        }
        sharedPreferences?.edit(commit = true) {
            putInt(PREFERENCE_PAGE_COUNT,pageCount?:-1)
        }
        return sharedPreferences?.getInt(PREFERENCE_PAGE_COUNT,-1)!!
    }

    fun getMessage() = sharedPreferences?.getString(PREFERENCE_GREETING,"Welcome!")

    fun getControl() = sharedPreferences?.getBoolean(PREFERENCE_CONTROL,true)
}