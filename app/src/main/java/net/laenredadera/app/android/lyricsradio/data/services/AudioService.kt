package net.laenredadera.app.android.lyricsradio.data.services

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AudioService @Inject constructor(@ApplicationContext context: Context){
    // Declare an audio manager
    private val audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager

    // on below line we are creating variables for
    // volume level, max volume, volume percent.


    fun volume(): Float {
        val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        return (volumeLevel.toFloat() / maxVolumeLevel * 100)
    }
}