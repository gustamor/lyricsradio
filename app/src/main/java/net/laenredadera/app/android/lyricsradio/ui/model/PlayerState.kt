package net.laenredadera.app.android.lyricsradio.ui.model

sealed class PlayerState {
    data object Idle: PlayerState()
    data object Loading: PlayerState()
    data object Playing : PlayerState()
    data object Paused :PlayerState()
    data class SongInfo(
        val artist: String,
        val title: String,
        val albumCover: String?
    )
    data class Error(val message: String):PlayerState()
}