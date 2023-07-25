package net.laenredadera.app.android.lyricsradio.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.services.LastFMService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import net.laenredadera.app.android.lyricsradio.ui.CoverState
import javax.inject.Inject

class MediaInfoRepository @Inject constructor(private val lastFM: LastFMService) {
    private var prevArtist: String = ""
    private var prevTitle: String = ""
    suspend fun getAlbumMbId(artistName: String, trackName: String): String {
        return lastFM.getAlbumMbId(artistName, trackName)
    }
    suspend fun getAlbumNameByMbID(MbID: String): String {
        return lastFM.getAlbumNameByMbID(MbID)
    }
    suspend fun getAlbumName(artistName: String, trackName: String): Flow<String> {
        return lastFM.getAlbumName(artistName, trackName)
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

    suspend fun getTrackMbID(artistName: String, trackName: String): String =
        lastFM.getTrackInfoMbID(artistName,trackName)

}
