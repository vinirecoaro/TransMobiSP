package br.vino.transmobisp.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.vino.transmobisp.BuildConfig
import br.vino.transmobisp.model.Vehicle
import br.vino.transmobisp.model.VehicleResponse
import br.vino.transmobisp.service.olho_vivo.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel : ViewModel() {

    private val _vehicles = MutableLiveData<MutableList<Vehicle>>()
    val vehicles: LiveData<MutableList<Vehicle>> = _vehicles
    private val apiKey = BuildConfig.OLHO_VIVO_API_KEY

  /*  init {
        fetchVeiculos()
    }*/

    fun fetchVeiculos() {
        val service = RetrofitClient.instance
        service.authenticate(apiKey).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful && response.body() == true) {
                    Log.e("API", response.message())
                    service.getVehicles().enqueue(object : Callback<VehicleResponse> {
                        override fun onResponse(call: Call<VehicleResponse>, response: Response<VehicleResponse>) {
                            if (response.isSuccessful) {
                                val vehicles = response.body()?.l?.flatMap { it.vs } ?: emptyList()
                                if (vehicles.isNotEmpty()) {
                                    _vehicles.value = vehicles.toMutableList()
                                }
                            } else {
                                Log.e("API", "${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(call: Call<VehicleResponse>, t: Throwable) {
                            // Tratar erros
                        }
                    })
                } else {
                    Log.e("API", "${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                // Tratar erros
            }
        })
    }



}