package net.laenredadera.app.android.lyricsradio.data.repositories

import net.laenredadera.app.android.lyricsradio.data.services.AudioService
import javax.inject.Inject

class AudioServiceRepository @Inject constructor(
    private val audioService: AudioService
)
{
    fun getAudioServiceVolume():Float = audioService.volume()
}