package net.laenredadera.app.android.lyricsradio.data.repositories

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject

class MediaServiceRepository @Inject constructor(private val service: RadioReceiverService) {

    fun initPlayer() {
        service.initPlayer()
    }
     fun isPlaying(): Boolean {
       return  service.isPlaying.value
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
    fun getVolume():Float {
        return service.getVolume()
    }

    fun release() {
        service.release()
    }

    fun setVolume(vol: Float) {
        service.setVolume(vol)
    }

}