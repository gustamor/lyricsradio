package net.laenredadera.app.android.lyricsradio.data.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.laenredadera.app.android.lyricsradio.data.network.RadioStationsApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://radios.gustavomoreno.es/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesQuoteApiClient(retrofit: Retrofit) : RadioStationsApiClient {
          return retrofit.create(RadioStationsApiClient::class.java)
    }
}