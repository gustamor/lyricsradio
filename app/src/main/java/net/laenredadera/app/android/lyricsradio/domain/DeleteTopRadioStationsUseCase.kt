package net.laenredadera.app.android.lyricsradio.domain

import net.laenredadera.app.android.lyricsradio.data.repositories.RadioStationsRepository
import javax.inject.Inject

class DeleteTopRadioStationsUseCase @Inject constructor(private val radioStationsRepository: RadioStationsRepository) {
   suspend operator fun invoke() = radioStationsRepository.deleteAllTopStations()

}