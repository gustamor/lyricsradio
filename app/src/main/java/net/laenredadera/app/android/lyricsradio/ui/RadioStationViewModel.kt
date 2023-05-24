package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.ui.domain.GetRadioStationsUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel
import javax.inject.Inject

@HiltViewModel
class RadioStationViewModel @Inject constructor(private val getRadioStationsUseCase: GetRadioStationsUseCase) :
    ViewModel() {

    private var _stations = MutableLiveData<List<RadioStationModel>?>()
    val stations: LiveData<List<RadioStationModel>?> = _stations

    fun getStations() {
       viewModelScope.launch {
            _stations.value = getRadioStationsUseCase()
           Log.i("GusMorUseCase", _stations.value.toString())

        }
    }
}