package com.hasanalic.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hasanalic.rickandmorty.util.CustomSharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val customSharedPreferences = CustomSharedPreferences(this)
        StyleableToast.makeText(this,customSharedPreferences.getMessage(), R.style.customToast).show()
        customSharedPreferences.setMessage("Hello!")
        setContentView(R.layout.activity_main)
    }
}