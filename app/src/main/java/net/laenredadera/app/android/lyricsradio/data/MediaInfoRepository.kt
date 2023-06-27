package net.laenredadera.app.android.lyricsradio.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.laenredadera.app.android.lyricsradio.data.services.LastFMService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.Wiki
import javax.inject.Inject

class MediaInfoRepository @Inject constructor(private val lastFM: LastFMService) {

    suspend fun getAlbumMbId(artistName: String, trackName: String): String {
       return lastFM.getAlbumMbId(artistName, trackName)
    }

    suspend fun getAlbumCover(artistName: String, trackName: String): String {
            var a = lastFM.getAlbumCover(artistName,trackName)
        Log.i("GusMor medioinfo: ", a)
            return  a
    }

    suspend fun getAlbumInfoWiki(mbid: String): Wiki {

        return lastFM.getAlbumInfoWiki(mbid)
    }

}
