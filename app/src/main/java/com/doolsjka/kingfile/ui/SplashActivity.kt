package com.doolsjka.kingfile.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.doolsjka.kingfile.KingApp
import com.doolsjka.kingfile.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var b: ActivitySplashBinding
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        b = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.logoContainer.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 900 })
        Handler(Looper.getMainLooper()).postDelayed({
            val prefs = getSharedPreferences(KingApp.PREFS, MODE_PRIVATE)
            val next = if (!prefs.getBoolean("welcomed", false))
                Intent(this, WelcomeActivity::class.java)
            else Intent(this, MainActivity::class.java)
            startActivity(next)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 1800)
    }
}
