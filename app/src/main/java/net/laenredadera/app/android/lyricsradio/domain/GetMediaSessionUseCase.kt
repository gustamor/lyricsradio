package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject

class GetMediaSessionUseCase @Inject constructor(private val radioReceiverService: RadioReceiverService){

    operator fun invoke():RadioReceiverService {
       return radioReceiverService
    }
}