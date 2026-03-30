package com.doolsjka.kingfile

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class KingApp : Application() {
    companion object {
        const val PREFS = "kf_prefs"
        const val CH_MEDIA = "kf_media"
        const val CH_DOWNLOAD = "kf_download"
        const val CH_GENERAL = "kf_general"
        lateinit var instance: KingApp private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NotificationManager::class.java)
            listOf(
                NotificationChannel(CH_MEDIA, "وسائط", NotificationManager.IMPORTANCE_LOW),
                NotificationChannel(CH_DOWNLOAD, "تحميل", NotificationManager.IMPORTANCE_DEFAULT),
                NotificationChannel(CH_GENERAL, "عام", NotificationManager.IMPORTANCE_DEFAULT)
            ).forEach { nm?.createNotificationChannel(it) }
        }
    }
}
