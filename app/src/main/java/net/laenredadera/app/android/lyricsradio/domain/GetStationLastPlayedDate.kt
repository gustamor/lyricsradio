package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.RadioStationsRepository
import javax.inject.Inject

class GetStationLastPlayedDate @Inject constructor(private val radioStationsRepository: RadioStationsRepository) {
    suspend operator fun invoke(id: Int): Long = radioStationsRepository.getLastPlayedDate(id)
}