package net.laenredadera.app.android.lyricsradio.data.repositories

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.CoverState
import net.laenredadera.app.android.lyricsradio.data.services.LastFMService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import javax.inject.Inject

class MediaInfoRepository @Inject constructor(private val lastFM: LastFMService) {
    private var prevArtist: String = ""
    private var prevTitle: String = ""
    suspend fun getAlbumMbId(artistName: String, trackName: String): String {
        return lastFM.getAlbumMbId(artistName, trackName)
    }

    suspend fun getAlbumCover(artistName: String, trackName: String): Flow<CoverState> =
        coroutineScope {
            withContext(Dispatchers.IO) {
                flow {
                    if (prevArtist != artistName && prevTitle != trackName) {
                        prevArtist = artistName
                        prevTitle = trackName
                     //   emit(CoverState.Loading)
                    }
                    try {
                        val url = lastFM.getAlbumCover(artistName, trackName)
                        emit(CoverState.Success(url))
                    } catch (e: Exception) {
                        emit(CoverState.Error(e))
                    }
                }
            }
        }


    suspend fun getArtistMbId(artistName: String, trackName: String): String {
        return lastFM.getArtistMbId(artistName, trackName)
    }

    suspend fun getAlbumInfoWiki(mbid: String): Wiki {
        return lastFM.getAlbumInfoWiki(mbid)
    }

}
