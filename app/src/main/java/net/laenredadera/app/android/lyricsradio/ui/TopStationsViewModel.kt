package net.laenredadera.app.android.lyricsradio.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationTopStationsUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationLastPlayedDate
import net.laenredadera.app.android.lyricsradio.domain.model.TopStationsModel
import net.laenredadera.app.android.lyricsradio.ui.model.TopStationUi
import net.laenredadera.app.android.lyricsradio.ui.model.toData
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class TopStationsViewModel @Inject constructor(
    getRadioStationTopStationsUseCase: GetRadioStationTopStationsUseCase,
    private val getStationLastPlayedDate: GetStationLastPlayedDate,
) : ViewModel() {

    private val _topStations: Flow<List<TopStationsModel?>> = getRadioStationTopStationsUseCase()
    var topStations: Flow<List<TopStationUi?>> = _topStations.map { stations ->
        stations.filter { station ->
            (station?.lastTimePlayed ?: 0) > 1
        }.map { station ->
            station?.toData()
        }
    }
    private var _lastPlayedDate = MutableStateFlow("")
    val lastPlayedDate: StateFlow<String> = _lastPlayedDate

    suspend fun getItemLastDate(id: Int) {
        val currentDate: Long = getStationLastPlayedDate(id)
        if (currentDate > 1) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = dateFormat.format(currentDate)
            _lastPlayedDate.value = formattedDate
        } else {
            _lastPlayedDate.value = "No played still"
        }
    }
}