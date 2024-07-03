package br.vino.transmobisp.ui.lines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.vino.transmobisp.databinding.FragmentLinesBinding
import com.google.android.material.snackbar.Snackbar

class LinesFragment : Fragment() {

    private var _binding: FragmentLinesBinding? = null
    private val viewModel by viewModels<LinesViewModel>()
    private lateinit var adapter: LineListAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLinesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LineListAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        viewModel.fetchVeiculosLine()
        setupListeners()

        return root
    }

    private fun setupListeners(){
        viewModel.vehiclesLine.observe(viewLifecycleOwner){vehiclesLineList ->
            if (vehiclesLineList.isNotEmpty()){
                adapter.updateVehiclesLine(vehiclesLineList)
            }else{
                Snackbar.make(binding.recyclerView, "Nenhuma informação encontrada", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}