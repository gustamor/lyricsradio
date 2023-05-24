package net.laenredadera.app.android.lyricsradio.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.laenredadera.app.android.lyricsradio.data.services.network.LyricsApiClient
import net.laenredadera.app.android.lyricsradio.data.services.network.RadioStationsApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StationsListNetworkModule {

    @Singleton
    @Provides
    @Named("StationsClient")

    fun providesRetrofitRadiosList(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.npoint.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
        fun providesStationsApiClient(@Named("StationsClient") retrofit: Retrofit): RadioStationsApiClient {
        return retrofit.create(RadioStationsApiClient::class.java)
    }
}
@Module
@InstallIn(SingletonComponent::class)
class LyricsNetworkModule {

    @Provides
    @Named("LyricsClient")
    @Singleton
    fun providesRetrofitLyrics() : Retrofit {
        return Retrofit.Builder()
             .baseUrl("https://lyricas-api.laenredadera.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesLyricsApiClient(@Named("LyricsClient") retrofit: Retrofit): LyricsApiClient {
        return retrofit.create(LyricsApiClient::class.java)
    }
}
