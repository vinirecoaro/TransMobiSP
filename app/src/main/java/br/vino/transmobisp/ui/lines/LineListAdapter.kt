package br.vino.transmobisp.ui.lines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.vino.transmobisp.R
import br.vino.transmobisp.model.VehicleLine

class LineListAdapter(private var lines : List<VehicleLine>) : RecyclerView.Adapter<LineListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val line = lines[position]
        holder.sign.text = line.c
        val originDestinationText = "${line.lt1} - ${line.lt0}"
        holder.origin_destination.text = originDestinationText
    }

    override fun getItemCount(): Int {
        return lines.size
    }

    fun updateVehiclesLine(vehiclesLine: List<VehicleLine>){
        lines = vehiclesLine
        notifyDataSetChanged()
    }

    // ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sign: TextView = itemView.findViewById(R.id.sign)
        val origin_destination: TextView = itemView.findViewById(R.id.origin_destination)
    }
}