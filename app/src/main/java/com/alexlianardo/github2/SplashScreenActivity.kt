package com.alexlianardo.github2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val github: ImageView = findViewById(R.id.github)
        val madeby: TextView = findViewById(R.id.made_by)
        val alex: TextView = findViewById(R.id.alexlianardo)

        github.alpha = 0f
        madeby.alpha = 0f
        alex.alpha = 0f
        madeby.animate().setDuration(4000).alpha(1f)
        alex.animate().setDuration(4000).alpha(1f)
        github.animate().setDuration(4000).alpha(1f).withEndAction{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}