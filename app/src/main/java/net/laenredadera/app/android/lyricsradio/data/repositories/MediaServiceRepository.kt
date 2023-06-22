package net.laenredadera.app.android.lyricsradio.data.repositories

import android.net.Uri
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject

class MediaServiceRepository @Inject constructor(private val service: RadioReceiverService) {

    fun initPlayer() {
        service.initPlayer()
    }


    fun play() {
        service.play()
    }

    fun pause() {
        service.pause()
    }

    fun stop() {
        service.stop()
    }

    fun addMedia(uri: Uri) {
        service.addMedia(uri)
    }

    fun release() {
        service.release()
    }

    fun setVolume(vol: Float) {
        service.setVolume(vol)
    }
}