package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.MediaServiceRepository
import javax.inject.Inject


class GetMediaPrepareUseCase @Inject constructor(private val media: MediaServiceRepository){
    operator fun invoke() = media.initPlayer()
}