package net.laenredadera.app.android.lyricsradio

import net.laenredadera.app.android.lyricsradio.domain.model.SongModel

sealed interface PlayingSongInfoState{
    object Loading: PlayingSongInfoState
    object Updating: PlayingSongInfoState
    data class Success(val artist:String, val title:String): PlayingSongInfoState
    data class Error(val exception: Exception): PlayingSongInfoState
}