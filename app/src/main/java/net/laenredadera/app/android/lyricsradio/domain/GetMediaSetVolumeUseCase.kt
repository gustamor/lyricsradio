package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.MediaServiceRepository
import javax.inject.Inject

class GetMediaSetVolumeUseCase  @Inject constructor(private val media: MediaServiceRepository){
    operator fun invoke(vol: Float) = media.setVolume(vol)
}

