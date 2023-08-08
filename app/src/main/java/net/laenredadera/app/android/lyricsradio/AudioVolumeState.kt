package net.laenredadera.app.android.lyricsradio

sealed interface AudioVolumeState{
    object Initialized: AudioVolumeState
    object Error : AudioVolumeState
    data class UpdatedVolume(val volume: Float): AudioVolumeState
}