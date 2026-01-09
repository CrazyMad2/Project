package com.crazymaddoctor.urbanescape.domain.model

data class Adventure(
    val id: String,
    val cityId: String,
    val title: String,
    val difficulty: String,
    val durationMin: Int,
    val distanceKm: Double,
    val coverImageUrl: String? = null,
    val pointsCost: Int = 0,
    val pointsReward: Int = 0,
    val isActive: Boolean = true
)
