package br.vino.transmobisp.model.stops_from_line

data class VehicleStatus(
    val p: String,
    val t: String,
    val a: Boolean,
    val ta: String,
    val py: Double,
    val px: Double
)