package net.laenredadera.app.android.lyricsradio.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.laenredadera.app.android.lyricsradio.data.model.RadioStationItem
import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModel

class RadioStationViewModel : ViewModel() {

    private val _stations = MutableLiveData<RadioStationModel>()
    val stations: LiveData<RadioStationModel> = _stations

    fun getStations() {
    }
}