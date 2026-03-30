package com.doolsjka.kingfile.core
import androidx.media3.session.MediaSessionService
import androidx.media3.session.MediaSession
import androidx.media3.exoplayer.ExoPlayer

class KingMediaService : MediaSessionService() {
    private var session: MediaSession? = null
    override fun onCreate() {
        super.onCreate()
        val player = ExoPlayer.Builder(this).build()
        session = MediaSession.Builder(this, player).build()
    }
    override fun onGetSession(ci: MediaSession.ControllerInfo) = session
    override fun onDestroy() {
        session?.run { player.release(); release(); session = null }
        super.onDestroy()
    }
}
