package br.vino.transmobisp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.vino.transmobisp.R
import br.vino.transmobisp.databinding.FragmentHomeBinding
import br.vino.transmobisp.ui.component.BitmapHelper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync {googleMap ->
            addMarkers(googleMap)
        }
        return root
    }

    private fun addMarkers(googleMap : GoogleMap){
        places.forEach{place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
                    .icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.bus_icon,
                            ContextCompat.getColor(requireContext(), R.color.black)
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