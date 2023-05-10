package net.laenredadera.app.android.lyricsradio.data.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://radios.gustavomoreno.es")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
