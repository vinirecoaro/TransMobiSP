package br.vino.transmobisp.ui.stops_from_line_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.vino.transmobisp.R
import br.vino.transmobisp.model.VehicleLine

class StopsFromLineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stops_from_line)

        val vehicleLine = intent.getSerializableExtra("vehicleLine") as? VehicleLine

        supportActionBar?.title = "Paradas"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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