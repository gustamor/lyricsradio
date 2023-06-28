package net.laenredadera.app.android.lyricsradio.data.services

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.System.getString
import android.util.Log
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.qualifiers.ApplicationContext
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.data.services.network.LastFMApiClient
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import javax.inject.Inject

class LastFMService @Inject constructor(private val api: LastFMApiClient, @ApplicationContext private val context: Context
){
    fun getApiKey(): String {
        return context.resources.getString(R.string.api_lastfm_key)
    }

    suspend fun getAlbumCover(artistName: String, trackName: String): String {

        var trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        return if ( trackInfo.isSuccessful) {
            var cover = trackInfo.body()!!.track.album.image[3].text
            if (cover == null)   cover = trackInfo.body()!!.track.album.image[2].text
            return cover
        } else {
            return ""
        }
    }
    suspend fun getAlbumMbId(artistName: String, trackName: String): String  {
        var trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        return if ( trackInfo.isSuccessful) {
            var mbid = trackInfo.body()!!.track.album.mbid
            return mbid
        } else {
            return ""
        }
    }
    suspend fun getArtistMbId(artistName: String, trackName: String): String  {
        var trackInfo = api.getTrackInfo(getApiKey(), artistName, trackName)
        return if ( trackInfo.isSuccessful) {
            var mbid = trackInfo.body()!!.track.artist.mbid
            return mbid
        } else {
            return ""
        }
    }

    suspend fun getAlbumInfoWiki(mbid: String): Wiki {
        var albumInfo = api.getAlbumInfo(getApiKey(), mbid)
        return if (albumInfo.isSuccessful) {

            var wiki = albumInfo.body()!!.album.wiki
            return wiki
        } else {
            return  Wiki("0","","")
        }

    }


}
