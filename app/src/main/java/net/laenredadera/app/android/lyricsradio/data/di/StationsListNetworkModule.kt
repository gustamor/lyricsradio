package net.laenredadera.app.android.lyricsradio.data.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.laenredadera.app.android.lyricsradio.R
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

    fun providesRetrofitRadiosList(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_radiostations))
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
    fun providesRetrofitLyrics(@ApplicationContext context: Context) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_lyrics))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesLyricsApiClient(@Named("LyricsClient") retrofit: Retrofit): LyricsApiClient {
        return retrofit.create(LyricsApiClient::class.java)
    }
}


