package com.hasanalic.rickandmorty

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.hasanalic.rickandmorty.util.CustomSharedPreferences

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val customSharedPreferences = CustomSharedPreferences(this)
        val textView: TextView = findViewById(R.id.textViewSplash)
        textView.text = customSharedPreferences.getMessage()
        customSharedPreferences.setMessage("Hello!")

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}