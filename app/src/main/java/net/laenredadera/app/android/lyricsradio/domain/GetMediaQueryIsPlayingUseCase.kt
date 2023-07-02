package net.laenredadera.app.android.lyricsradio.domain

import javax.inject.Inject

class GetMediaQueryIsPlayingUseCase @Inject constructor(private val getExoPlayerUseCase: GetExoPlayerUseCase) {
    operator fun invoke() = getExoPlayerUseCase().isPlaying

}

