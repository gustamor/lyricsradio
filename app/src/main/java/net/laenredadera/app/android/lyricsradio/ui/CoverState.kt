package net.laenredadera.app.android.lyricsradio.ui

sealed interface CoverState {
    object Loading: CoverState
    data class Success(val url: String): CoverState
    data class Error(val exception: Exception): CoverState
}


