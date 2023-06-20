package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetRadioStationsUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import java.io.Console
import javax.inject.Inject

@HiltViewModel
class RadioStationViewModel @Inject constructor(private val getRadioStationsUseCase: GetRadioStationsUseCase) :
    ViewModel() {

    private var _stations = MutableLiveData<List<RadioStationModel>?>()
    val stations: LiveData<List<RadioStationModel>?> = _stations

    fun getStations() {
       viewModelScope.launch {
            _stations.value = getRadioStationsUseCase()
           Log.i("GusMor", _stations.value.toString())
        }
    }
}