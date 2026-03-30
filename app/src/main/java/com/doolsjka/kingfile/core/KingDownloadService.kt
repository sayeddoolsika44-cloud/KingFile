package com.doolsjka.kingfile.core
import android.app.Service
import android.content.Intent
import android.os.IBinder
class KingDownloadService : Service() {
    override fun onBind(i: Intent?): IBinder? = null
    override fun onStartCommand(i: Intent?, f: Int, id: Int) = START_NOT_STICKY
}
