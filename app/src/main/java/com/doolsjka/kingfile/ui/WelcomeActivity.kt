package com.doolsjka.kingfile.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import android.view.animation.AnimationSet
import androidx.appcompat.app.AppCompatActivity
import com.doolsjka.kingfile.KingApp
import com.doolsjka.kingfile.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var b: ActivityWelcomeBinding
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        b = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(b.root)

        // اسم المالك — يظهر من الأعلى
        b.ownerName.startAnimation(AnimationSet(true).apply {
            addAnimation(AlphaAnimation(0f,1f).apply { duration=900; startOffset=300 })
            addAnimation(TranslateAnimation(0f,0f,-50f,0f).apply { duration=900; startOffset=300 })
        })
        // اسم التطبيق
        b.appTitle.startAnimation(AnimationSet(true).apply {
            addAnimation(AlphaAnimation(0f,1f).apply { duration=800; startOffset=600 })
            addAnimation(TranslateAnimation(0f,0f,-30f,0f).apply { duration=800; startOffset=600 })
        })
        // زر Start King
        b.startBtn.startAnimation(AlphaAnimation(0f,1f).apply { duration=700; startOffset=1000; fillAfter=true })

        b.startBtn.setOnClickListener {
            getSharedPreferences(KingApp.PREFS, MODE_PRIVATE).edit().putBoolean("welcomed", true).apply()
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
