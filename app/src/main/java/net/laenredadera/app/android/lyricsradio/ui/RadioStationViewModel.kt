package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.ui.domain.GetRadioStationsUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import java.io.Console
import javax.inject.Inject

@HiltViewModel
class RadioStationViewModel @Inject constructor(private val getRadioStationsUseCase: GetRadioStationsUseCase) :
    ViewModel() {

    private val _stations = mutableListOf<RadioStationModel>()
    val stations: List<RadioStationModel> = _stations

    fun getStations() {
        viewModelScope.launch {
            val _stations = getRadioStationsUseCase()
            Log.i("GusMor", _stations.toString())
        }
    }
}