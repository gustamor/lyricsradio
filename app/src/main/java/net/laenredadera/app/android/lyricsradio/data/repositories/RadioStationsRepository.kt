package net.laenredadera.app.android.lyricsradio.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import net.laenredadera.app.android.lyricsradio.data.db.TracksDao
import net.laenredadera.app.android.lyricsradio.data.services.RetrofitService
import net.laenredadera.app.android.lyricsradio.data.services.network.model.RadioStationItemResponse
import net.laenredadera.app.android.lyricsradio.domain.model.MostPlayedStations
import net.laenredadera.app.android.lyricsradio.domain.model.RadioStationModel
import net.laenredadera.app.android.lyricsradio.domain.model.toData
import javax.inject.Inject

class RadioStationsRepository @Inject constructor(private val retrofitService: RetrofitService, private val dao: TracksDao) {
    suspend fun getAllRadioStations(): List<RadioStationModel> {
        val response: List<RadioStationItemResponse> = retrofitService.getAllRadioStations()
        return response.map {
            val station = MostPlayedStations(it.id, it.enabled,it.name,1,0)
            insertMostPlayedStationEntity(station)
            it.toData() }
    }
    fun getOrderedMostPlayedStation():Flow<List<MostPlayedStations?>> =
        dao.getOrderedMostPlayedStation().map { station -> station.map { it?.toData() } }
    suspend fun updateOneTimePlayed(id: Int) {
        return withContext(Dispatchers.IO) {
            dao.updateMostPlayedStationEntity(id)
        }
    }
    suspend fun getNumberOfTimesPlayed(id: Int):Int {
        return withContext(Dispatchers.IO) {
            dao.getNumberOfTimesPlayed(id)
        }
    }
    suspend fun insertMostPlayedStationEntity(station: MostPlayedStations) {
        return withContext(Dispatchers.IO) {
            dao.insertMostPlayedStationEntity(station.toData())
        }
    }
}