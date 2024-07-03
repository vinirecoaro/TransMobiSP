package br.vino.transmobisp.ui.stops_from_line_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.vino.transmobisp.databinding.ActivityStopsFromLineBinding
import br.vino.transmobisp.model.VehicleLine
import br.vino.transmobisp.ui.main_activity.fragments.lines.LineListAdapter

class StopsFromLineActivity : AppCompatActivity() {

    private val binding by lazy { ActivityStopsFromLineBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<StopsFromLineViewModel>()
    private lateinit var adapter: StopsFromLineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val vehicleLine = intent.getSerializableExtra("vehicleLine") as? VehicleLine

        viewModel.getStopsFromLine(vehicleLine!!.cl.toString())

        binding.stopsFromLineRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StopsFromLineAdapter(emptyList())
        binding.stopsFromLineRecyclerView.adapter = adapter

        supportActionBar?.title = "Paradas da linha ${vehicleLine.c}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupListeners()

    }

    private fun setupListeners(){
        viewModel.stopsFromLine.observe(this){stopsFromLine ->
            adapter.updateStopsFromline(stopsFromLine.ps)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
               finish() // Voltar quando o botão de voltar (seta) for pressionado
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}