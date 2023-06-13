package net.laenredadera.app.android.lyricsradio.data

import android.util.Log
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import javax.inject.Inject

class OnlineStationRepository @Inject constructor(private val service: RadioReceiverService){
    suspend fun getStation() {}
    suspend fun getStationName() {}
    suspend fun getArtist():String? {
      while(true) {
          service.icyMetadata()
          delay(1001)
          return service.artistName.value
      }
    }
    suspend fun getTitle(): String? {
        while(true) {
            service.icyMetadata()
            delay(1001)
            return service.songName.value
        }
    }
}