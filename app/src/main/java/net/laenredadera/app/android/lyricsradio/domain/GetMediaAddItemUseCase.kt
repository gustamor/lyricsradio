package net.laenredadera.app.android.lyricsradio.domain

import android.net.Uri
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject


class GetMediaAddItemUseCase @Inject constructor(private val radioReceiverService: RadioReceiverService){

    operator fun invoke(uri: Uri) {
        return radioReceiverService.addMedia(uri)
    }
}