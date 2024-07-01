package br.vino.transmobisp.model

data class VehicleLine(
    val c: String, // Line code
    val cl: Int, // Identification line code
    val lt0: String, // Line sign - direction 1
    val lt1: String, // Line sign - direction 2
    val qv: Int, // Vehicle amount
    val vs: List<Vehicle> // Vehicle List
)
