package br.vino.transmobisp.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import br.vino.transmobisp.R
import br.vino.transmobisp.databinding.FragmentHomeBinding
import br.vino.transmobisp.model.Vehicle
import br.vino.transmobisp.ui.component.BitmapHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val viewModel  by viewModels<MapViewModel>()
    private val places = arrayListOf(
        Place(
            "Google",
            LatLng(-23.5868031,-46.6843406),
            "Av. Brg. Faria Lima, 3477 - 18° Andar - Itaim Bibi, São Paulo - SP",
            4.8f
        ),
        Place(
            "Parque",
            LatLng(-23.5899619, -46.66747),
            "Av. República do Líbano, 1111 - Ibirapuera, São Paulo - SP",
            4.9f
        )
    )
    private lateinit var googleMap: GoogleMap


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync {gMap ->
            googleMap = gMap
            viewModel.fetchVeiculos()

            googleMap.setOnMapLoadedCallback {

                //Found extreme points between places
                val bounds = LatLngBounds.builder()
                places.forEach{
                    bounds.include(it.latLng)
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }

        }

        setupListeners()

        return root
    }

    private fun setupListeners(){
        viewModel.vehicles.observe(viewLifecycleOwner) { vehicles ->
            addMarkers(googleMap, vehicles)
        }
    }

    private fun addMarkers(googleMap : GoogleMap, vehicles: List<Vehicle>){
        googleMap.clear()  // Clear existing markers
        vehicles.forEach { vehicle ->
            val position = LatLng(vehicle.py, vehicle.px)  // Assume Vehicle has latitude and longitude properties
            googleMap.addMarker(
                MarkerOptions()
                    .title("Vehicle ${vehicle.p}")
                    .position(position)
                    .icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.bus_icon,
                            ContextCompat.getColor(requireContext(), R.color.red)
                        )
                    )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

data class Place(
    val name : String,
    val latLng : LatLng,
    val address : String,
    val rating : Float
)
/*

googleMap.setOnMapLoadedCallback {

    //Found extreme points between places
    val bounds = LatLngBounds.builder()
    places.forEach{
        bounds.include(it.latLng)
    }
    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
}*/
