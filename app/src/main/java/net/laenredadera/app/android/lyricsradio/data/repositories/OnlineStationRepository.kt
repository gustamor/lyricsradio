package net.laenredadera.app.android.lyricsradio.data.repositories

import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import javax.inject.Inject

class OnlineStationRepository @Inject constructor(
    private val service: RadioReceiverService,
    private val getExoPlayerUseCase: GetExoPlayerUseCase
) {

    suspend fun getStation() {}
    suspend fun getStationName() {

    }

    suspend fun getArtist(): String {

            service.icyMetadata()

            return service.artistName.value

    }

    suspend fun getTitle(): String {

            service.icyMetadata()
            return service.songName.value



    }
}