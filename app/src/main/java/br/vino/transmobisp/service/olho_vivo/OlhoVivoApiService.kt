package br.vino.transmobisp.service.olho_vivo

import br.vino.transmobisp.model.ArrivalForecastResponse
import br.vino.transmobisp.model.Line
import br.vino.transmobisp.model.Stop
import br.vino.transmobisp.model.VehicleResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OlhoVivoApiService {
    @POST("Login/Autenticar")
    fun authenticate(@Query("token") token: String): Call<Boolean>

    @GET("Posicao")
    fun getVehicles(): Call<VehicleResponse>

    @GET("Linha/Buscar")
    fun getLines(@Query("termosBusca") term: String): Call<List<Line>>

    @GET("Parada/BuscarParadasPorLinha")
    fun getStops(@Query("codigoLinha") lineCode: String): Call<List<Stop>>

    @GET("Previsao/Parada")
    fun getPrevisaoChegada(@Query("codigoParada") stopCode: String): Call<ArrivalForecastResponse>
}

