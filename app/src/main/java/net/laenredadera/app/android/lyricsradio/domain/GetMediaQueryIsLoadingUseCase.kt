package net.laenredadera.app.android.lyricsradio.domain

import javax.inject.Inject

class GetMediaQueryIsLoadingUseCase @Inject constructor(private val getExoPlayerUseCase: GetExoPlayerUseCase) {
    operator fun invoke(): Boolean = getExoPlayerUseCase().isLoading

}