package net.laenredadera.app.android.lyricsradio.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.laenredadera.app.android.lyricsradio.data.services.network.LyricsApiClient
import net.laenredadera.app.android.lyricsradio.data.services.network.RadioStationsApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StationsListNetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitRadiosList(): Retrofit {
        return Retrofit.Builder()
         //   .baseUrl("https://api.npoint.io/")
            .baseUrl("https://lyricas-api.laenredadera.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesStationsApiClient(retrofit: Retrofit): RadioStationsApiClient {
        return retrofit.create(RadioStationsApiClient::class.java)
    }
    @Singleton
    @Provides
    fun providesLyricsApiClient(retrofit: Retrofit): LyricsApiClient {
        return retrofit.create(LyricsApiClient::class.java)
    }

/*
    @Singleton
    @Provides
    fun providesRetrofitLyrics() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://genius-song-lyrics1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesLyricsApiClient(retrofit: Retrofit) : LyricsApiClient {
        return retrofit.create(LyricsApiClient::class.java)
    }*/


}