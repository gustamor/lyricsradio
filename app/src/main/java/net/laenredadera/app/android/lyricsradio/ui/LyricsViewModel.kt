package net.laenredadera.app.android.lyricsradio.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetLyricsUseCase
import net.laenredadera.app.android.lyricsradio.ui.model.LyricsModel
import javax.inject.Inject

@HiltViewModel
class LyricsViewModel @Inject constructor(private val getLyricsUseCase: GetLyricsUseCase) : ViewModel() {

    private var _lyrics = MutableLiveData<LyricsModel>()
    val lyrics : LiveData<LyricsModel> = _lyrics

    fun getLyrics(artist: String, title: String) {
        viewModelScope.launch {
            _lyrics.value = getLyricsUseCase(artist,title)
            Log.i("GusMorUseCase", _lyrics.value.toString())
        }
    }
}