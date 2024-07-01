package br.vino.transmobisp.service.olho_vivo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"

    val instance: OlhoVivoApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OlhoVivoApiService::class.java)
    }
}