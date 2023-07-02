package net.laenredadera.app.android.lyricsradio.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.laenredadera.app.android.lyricsradio.R
import net.laenredadera.app.android.lyricsradio.data.services.network.LastFMApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitLastFMModule {
    @Provides
    @Named("LastFmClient")
    @Singleton
    fun providesRetrofitLyrics(@ApplicationContext context: Context) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_lastfm))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesLastFmApiClient(@Named("LastFmClient") retrofit: Retrofit): LastFMApiClient {
        return retrofit.create(LastFMApiClient::class.java)
    }

}