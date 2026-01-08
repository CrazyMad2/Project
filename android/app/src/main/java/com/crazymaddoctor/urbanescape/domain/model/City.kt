package com.crazymaddoctor.urbanescape.domain.model

data class City(
    val id: String,
    val name: String,
    val country: String,
    val centerLat: Double,
    val centerLng: Double
)
