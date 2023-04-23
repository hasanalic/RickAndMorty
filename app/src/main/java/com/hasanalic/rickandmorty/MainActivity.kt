package com.hasanalic.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hasanalic.rickandmorty.util.CustomSharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val customSharedPreferences = CustomSharedPreferences(this)
        customSharedPreferences.setControl(true)
        customSharedPreferences.setPageCount()
        customSharedPreferences.setLocationControl(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}