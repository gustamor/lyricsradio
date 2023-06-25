package net.laenredadera.app.android.lyricsradio.data.repositories

import android.util.Log
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.db.PlayedTrackDataEntity
import net.laenredadera.app.android.lyricsradio.data.db.TracksDao
import net.laenredadera.app.android.lyricsradio.domain.model.PlayedTrackDataModel
import net.laenredadera.app.android.lyricsradio.domain.model.toData
import javax.inject.Inject

class TracksRepository @Inject constructor(private val dao: TracksDao) {


    suspend fun insertPlayedTrack(track: PlayedTrackDataModel) {
        withContext(Dispatchers.IO) {
            dao.insertPlayedTrack(track.toData())
            Log.i("GusMorTrackToData", track.toData().toString())
        }
    }


    fun getTracksInfo(): Flow<List<PlayedTrackDataModel>> =
        dao.getPlayedTracks().map { track -> track.map { it.toData() } }


}
