package net.laenredadera.app.android.lyricsradio.ui.model

sealed class PlayerIntent {
    data class LoadStation(
        val station: RadioStationModelUI) : PlayerIntent()
        data object PlayClicked: PlayerIntent()
        data object PauseClicked: PlayerIntent()
        data class VolumeChanged(val volume: Float) : PlayerIntent()


}