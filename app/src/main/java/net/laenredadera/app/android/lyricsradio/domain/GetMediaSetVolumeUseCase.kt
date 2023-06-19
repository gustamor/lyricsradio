package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.MediaServiceRepository
import javax.inject.Inject

class GetMediaSetVolumeUseCase  @Inject constructor(private val media: MediaServiceRepository){

    operator fun invoke(vol: Float) {
        return media.setVolume(vol)
    }
}