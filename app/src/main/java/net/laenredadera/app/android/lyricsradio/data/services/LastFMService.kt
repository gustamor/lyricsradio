package net.laenredadera.app.android.lyricsradio.data.services

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.data.services.network.LastFMApiClient
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import javax.inject.Inject

class LastFMService @Inject constructor(
    private val api: LastFMApiClient, @ApplicationContext private val context: Context
) {
    fun getApiKey(): String {
        return context.resources.getString(R.string.api_lastfm_key)
    }

    suspend fun getAlbumCover(artistName: String, trackName: String): String {
        var trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        return if (trackInfo.isSuccessful) {
            var cover = trackInfo.body()!!.track.album.image[3].text
            if (cover == null) cover = trackInfo.body()!!.track.album.image[2].text
            return cover
        } else {
            return ""
        }
    }

    suspend fun getAlbumMbId(artistName: String, trackName: String): String {
        val trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        if (trackInfo.isSuccessful) {
            return trackInfo.body()!!.track.album.mbid
        } else {
            return ""
        }
    }

    suspend fun getArtistMbId(artistName: String, trackName: String): String {
        val trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        if (trackInfo.isSuccessful) {
            return trackInfo.body()!!.track.artist.mbid
        } else {
            return ""
        }
    }

    suspend fun getAlbumInfoWiki(mbid: String): Wiki {
        val albumInfo = api.getAlbumInfo(getApiKey(), mbid)
        return if (albumInfo.isSuccessful) {
            albumInfo.body()!!.album.wiki
        } else {
            return Wiki("0", "", "")
        }
    }

    suspend fun getAlbumNameByMbID(MbID: String): String {
        val albumInfo = api.getAlbumInfo(getApiKey(), MbID)
        return if (albumInfo.isSuccessful) {
            albumInfo.body()!!.album.name
        } else {
            return " "
        }
    }

    suspend fun getTrackInfoMbID(artistName: String, trackName: String): String {
        val trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        return if (trackInfo.isSuccessful) {
            trackInfo.body()!!.track.mbid
        } else {
            ""
        }
    }

    suspend fun getAlbumName(artistName: String, trackName: String): Flow<String> =
        coroutineScope {
            withContext(Dispatchers.IO) {
                flow {
                    val trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
                    if (trackInfo.isSuccessful) {
                        try {
                            val  mbid =  trackInfo.body()!!.track.album.mbid
                            delay(500)
                            emit(getAlbumNameByMbID(mbid))
                        } catch (e: Exception) {

                        }

                    } else {
                        delay(1000)
                        emit("")
                    }
                }
            }
        }
}
