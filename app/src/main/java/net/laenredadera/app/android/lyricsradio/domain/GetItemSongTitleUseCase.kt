package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.OnlineStationRepository
import javax.inject.Inject

class GetItemSongTitleUseCase @Inject constructor(private val stationsRepository: OnlineStationRepository) {
    suspend operator fun invoke():String = stationsRepository.getTitle() ?: " "

}