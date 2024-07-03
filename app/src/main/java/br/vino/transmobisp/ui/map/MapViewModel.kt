package br.vino.transmobisp.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.vino.transmobisp.BuildConfig
import br.vino.transmobisp.model.Vehicle
import br.vino.transmobisp.model.VehicleResponse
import br.vino.transmobisp.service.olho_vivo.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            try {
                val service = RetrofitClient.instance
                val authResponse: Response<Boolean> = service.authenticate(apiKey)
                if (authResponse.isSuccessful && authResponse.body() == true) {
                    val vehicleResponse: Response<VehicleResponse> = service.getVehicles()
                    if (vehicleResponse.isSuccessful) {
                        val vehicles = vehicleResponse.body()?.l?.flatMap { it.vs } ?: emptyList()
                        if (vehicles.isNotEmpty()) {
                            _vehicles.value = vehicles.toMutableList()
                        }
                    } else {
                        Log.e("API", "${vehicleResponse.errorBody()?.string()}")
                    }
                } else {
                    Log.e("API", "${authResponse.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error fetching vehicles", e)
                // Tratar erros
            }
        }
    }



}