package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.MediaServiceRepository
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject


class GetMediaPlayUseCase @Inject constructor(private val media: MediaServiceRepository){

    operator fun invoke() {
        return media.play()
    }
}