package net.laenredadera.app.android.lyricsradio.ui

import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.laenredadera.app.android.lyricsradio.domain.GetAlbumCoverUseCase

import net.laenredadera.app.android.lyricsradio.domain.GetExoPlayerUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaAddItemUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPauseUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPlayUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaPrepareUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaQueryIsPlayingUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaSetVolumeUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetMediaStopUseCase
import net.laenredadera.app.android.lyricsradio.domain.GetStationDataUseCase

import net.laenredadera.app.android.lyricsradio.ui.model.RadioStationModelUI
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getExoPlayerUseCase: GetExoPlayerUseCase,
    private val getMediaQueryIsPlayingUseCase: GetMediaQueryIsPlayingUseCase,
    private val getMediaPlayUseCase: GetMediaPlayUseCase,
    private val getMediaStopUseCase: GetMediaStopUseCase,
    private val getMediaPauseUseCase: GetMediaPauseUseCase,
    private val getMediaPrepareUseCase: GetMediaPrepareUseCase,
    private val getMediaAddItemUseCase: GetMediaAddItemUseCase,
    private val getStationDataUseCase: GetStationDataUseCase,
    private val getMediaSetVolumeUseCase: GetMediaSetVolumeUseCase,
    private val getAlbumCoverUseCase: GetAlbumCoverUseCase,

) : ViewModel() {

    var newItem: Boolean = true
    private var _station: RadioStationModelUI? = null
    var station = MutableLiveData<RadioStationModelUI?>()

    private val _song = MutableStateFlow<List<String?>>(emptyList())
    var song: StateFlow<List<String?>> = _song.asStateFlow()

    private val _uiIsPlaying = MutableLiveData(false)
    val uiIsPlying: LiveData<Boolean> = _uiIsPlaying


    init {
        viewModelScope.launch {
            while (true) {
                    delay(100)
                if (_uiIsPlaying.value!!) {
                    _song.value = getStationDataUseCase()
                }
                else
                    _song.value = listOf("","")
                station.value = _station
                addListener()
            }
        }
    }

    private fun addListener() {
        getExoPlayerUseCase().addListener(
            object : Player.Listener {

                override fun onMediaItemTransition(
                    mediaItem: MediaItem?,
                    reason: Int
                ) {
                    _song.value = listOf(" ", " ")
                }
            }
        )
    }

    private fun updateServiceIsPlaying() {
       viewModelScope.launch {
           _uiIsPlaying.value = getMediaQueryIsPlayingUseCase()
        }
    }

    fun prepare() {
        viewModelScope.launch {
            _song.value = listOf(" ", " ")
            getMediaPrepareUseCase()
        }.apply {
            addListener()
        }
    }

    fun addMediaItem(uri: Uri) {
        stop()
        _song.value = listOf(" ", " ")
        viewModelScope.launch {
            getMediaAddItemUseCase(uri)
        }
    }
    fun addStationModel(radioStationModel: RadioStationModelUI) {
        _station = radioStationModel
    }

    fun play() {
        viewModelScope.launch {
            getMediaPlayUseCase()
            while (uiIsPlying.value != true) {
                delay(100)
                updateServiceIsPlaying()
            }

        }.apply {


        }
    }

    fun pause() {
        viewModelScope.launch {
            getMediaPauseUseCase()
        }.apply {
            updateServiceIsPlaying()
        }
    }

    fun stop() {
        viewModelScope.launch {
            getMediaStopUseCase()
        }.apply {
            updateServiceIsPlaying()
        }
    }

    fun setVolume(vol: Float) {
        viewModelScope.launch {
            getMediaSetVolumeUseCase(vol)
        }

    }

    fun albumCover() {
        viewModelScope.launch {
            try {
                while (true) {
                    delay(1000)
                    var cover = getAlbumCoverUseCase(_song.value[0]!!, _song.value[1]!!)
                    Log.i("GusMor coverUrl" , cover)

                }


            }catch (e: Exception) {}
             //   getAlbumCoverUseCase("Kreator", "Outcast")

            }


    }

}