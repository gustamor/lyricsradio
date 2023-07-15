package net.laenredadera.app.android.lyricsradio.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.laenredadera.app.android.lyricsradio.data.repositories.MediaServiceRepository
import javax.inject.Inject

class GetMediaGetVolumeUseCase  @Inject constructor(private val media: MediaServiceRepository){
    operator fun invoke(): Flow<Float> =  flow {
        while(true) {
            delay(100)
            emit(media.getVolume())
        }
    }


}