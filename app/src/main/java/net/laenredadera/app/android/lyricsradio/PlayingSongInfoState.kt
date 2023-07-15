package net.laenredadera.app.android.lyricsradio

sealed interface PlayingSongInfoState{
    object Loading: PlayingSongInfoState
    object Updating: PlayingSongInfoState
    data class Success(val artist:String, val title:String): PlayingSongInfoState
    data class Error(val exception: Exception): PlayingSongInfoState
}