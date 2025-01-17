package net.laenredadera.app.android.lyricsradio.ui.model

sealed interface CoverState {
    data  object Loading: CoverState
    data class Success(val url: String): CoverState
    data class Error(val exception: Exception): CoverState
}


