package net.laenredadera.app.android.lyricsradio.domain

import android.net.Uri
import net.laenredadera.app.android.lyricsradio.data.MediaServiceRepository
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject


class GetMediaAddItemUseCase @Inject constructor(private val media: MediaServiceRepository){

    operator fun invoke(uri: Uri) {
        return media.addMedia(uri)
    }
}