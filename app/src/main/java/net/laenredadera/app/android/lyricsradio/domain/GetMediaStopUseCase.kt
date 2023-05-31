package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject

class GetMediaStopUseCase  @Inject constructor(private val radioReceiverService: RadioReceiverService){

    operator fun invoke() {
        return radioReceiverService.stop()
    }
}