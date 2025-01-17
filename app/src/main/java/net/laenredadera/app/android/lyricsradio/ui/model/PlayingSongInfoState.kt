package net.laenredadera.app.android.lyricsradio.ui.model

sealed interface PlayingSongInfoState{
    data object Loading: PlayingSongInfoState
    data object Updating: PlayingSongInfoState
    data class Success(val artist:String, val title:String): PlayingSongInfoState
    data class Error(val exception: Exception): PlayingSongInfoState
}