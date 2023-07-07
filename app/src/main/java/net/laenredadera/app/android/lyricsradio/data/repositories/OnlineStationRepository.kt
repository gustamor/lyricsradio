package net.laenredadera.app.android.lyricsradio.data.repositories

import android.util.Log
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.CoverState
import net.laenredadera.app.android.lyricsradio.PlayingSongInfoState
import net.laenredadera.app.android.lyricsradio.data.services.RadioReceiverService
import javax.inject.Inject

class OnlineStationRepository @Inject constructor(
    private val service: RadioReceiverService,
) {
    private var prevArtist: String = " "
    private var prevTitle: String = " "
    suspend fun getStation() {}
    suspend fun getStationName() {}

    suspend fun songInfo(): Flow<PlayingSongInfoState> =
        coroutineScope {
            withContext(Dispatchers.IO) {
                flow {
                    if (prevArtist != getArtist() && prevTitle != getTitle()) {
                        prevArtist = getArtist()
                        prevTitle = getTitle()
                        emit(PlayingSongInfoState.Loading)
                    }
                    else
                        try {
                            delay(500)
                            emit(PlayingSongInfoState.Success(getArtist(), getTitle()))
                        } catch (e: Exception) {
                            emit(PlayingSongInfoState.Error(e))
                        }
                }
            }
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