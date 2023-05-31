package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject


class GetMediaPrepareUseCase @Inject constructor(private val radioReceiverService: RadioReceiverService){

    operator fun invoke() {
        return radioReceiverService.initPlayer()
    }
}