package net.laenredadera.app.android.lyricsradio.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.db.TracksDao
import net.laenredadera.app.android.lyricsradio.data.services.RetrofitService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItemResponse
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.domain.model.TopStationsModel
import net.laenredadera.app.android.lyricsradio.domain.model.toData
import javax.inject.Inject

class RadioStationsRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val dao: TracksDao
) {
    suspend fun getAllRadioStations(): List<RadioStationModel> {
        val response: List<RadioStationItemResponse> = retrofitService.getAllRadioStations()
        return response.map {
            val station = TopStationsModel(it.id, it.enabled, it.name, it.cover,it.address.icy_url, it.description, 1, 0)
            insertTopStationEntity(station)
            it.toData()
        }
    }

    fun getOrderedTopStations(): Flow<List<TopStationsModel?>> =
        dao.getOrderedTopStations().map { station -> station.map { it?.toData() } }

    suspend fun updateOneTimePlayed(id: Int) {
        return withContext(Dispatchers.IO) {
            dao.updateTopStationEntity(id)
        }
    }

    suspend fun getNumberOfTimesPlayed(id: Int): Int {
        return withContext(Dispatchers.IO) {
            dao.getNumberStationTimesPlayed(id)
        }
    }

    suspend fun insertTopStationEntity(station: TopStationsModel) {
        return withContext(Dispatchers.IO) {
            dao.insertTopStationEntity(station.toData())
        }
    }

    suspend fun getStationCover(stationId: Int):String {
        val response: List<RadioStationItemResponse> = retrofitService.getAllRadioStations()
        return withContext(Dispatchers.IO) {
            response.first { it.id == stationId }.cover
            }
        }

    suspend fun setLastPlayedDate(id: Int) {
        val currentTimeMillis = System.currentTimeMillis()
        return withContext(Dispatchers.IO) {
            dao.setLastTimePlayed(id,currentTimeMillis)
        }
    }
    suspend fun getLastPlayedDate(id: Int): Long {
        return withContext(Dispatchers.IO) {
            dao.getLastTimePlayed(id)
        }
    }


}