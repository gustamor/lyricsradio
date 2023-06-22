package net.laenredadera.app.android.lyricsradio.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.laenredadera.app.android.lyricsradio.data.db.TracksDao
import net.laenredadera.app.android.lyricsradio.data.db.TracksDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun providesTaskDao(tracksDataBase: TracksDataBase): TracksDao{
        return tracksDataBase.tracksDao()
    }


    @Provides
    @Singleton
    fun provideTracksDatabase(@ApplicationContext appContext: Context): TracksDataBase {
        return Room.databaseBuilder(appContext, TracksDataBase::class.java, "TracksDatabase")
            .build()
    }

}