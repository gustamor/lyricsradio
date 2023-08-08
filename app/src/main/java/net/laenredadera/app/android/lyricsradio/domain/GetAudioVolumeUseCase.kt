package net.laenredadera.app.android.lyricsradio.domain

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.laenredadera.app.android.lyricsradio.data.repositories.AudioServiceRepository
import javax.inject.Inject

class GetAudioVolumeUseCase @Inject constructor(private val audio: AudioServiceRepository) {

    operator fun invoke(): Flow<Float> = flow {
        while(true) {
            emit(audio.getAudioServiceVolume())
            delay(100)
        }
    }
}