package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationMostPlayedUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationsUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import net.laenredadera.app.android.lyricsradio.ui.model.toData
import javax.inject.Inject

@HiltViewModel
class RadioStationViewModel @Inject constructor(private val getRadioStationsUseCase: GetRadioStationsUseCase, private val getRadioStationMostPlayedUseCase: GetRadioStationMostPlayedUseCase) :
    ViewModel() {

    private var _stations = MutableLiveData<List<RadioStationModelUI>?>()
    val stations: LiveData<List<RadioStationModelUI>?> = _stations

    fun getStations() {
       viewModelScope.launch {
            _stations.value = getRadioStationsUseCase()!!.map {it.toData()}
           Log.i("GusMor", _stations.value.toString())
        }
    }
    fun getMostPlayedStation() {
        viewModelScope.launch {
          getRadioStationMostPlayedUseCase()
        }
    }


}