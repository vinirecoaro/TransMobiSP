package br.vino.transmobisp.model.stops_from_line

data class StopsFromLine(
    val hr: String,
    val ps: List<StopWithVehicles>
)