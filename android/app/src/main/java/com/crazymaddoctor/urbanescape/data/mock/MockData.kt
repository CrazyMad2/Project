package com.crazymaddoctor.urbanescape.data.mock

import com.crazymaddoctor.urbanescape.domain.model.Adventure
import com.crazymaddoctor.urbanescape.domain.model.City
import java.text.Normalizer

object MockData {

    private val cities = listOf(
        City("bcn", "Barcelona", "España", 41.3874, 2.1686),
        City("mad", "Madrid", "España", 40.4168, -3.7038),
        City("val", "Valencia", "España", 39.4699, -0.3763),
        City("sev", "Sevilla", "España", 37.3891, -5.9845)
    )

    private val adventures = listOf(
        Adventure("a1", "bcn", "La Puerta 13", "Media", 60, 2.2, pointsCost = 0, pointsReward = 20),
        Adventure("a2", "bcn", "Sombras del Raval", "Difícil", 90, 3.5, pointsCost = 40, pointsReward = 50),
        Adventure("a3", "mad", "El Silencio del Metro", "Fácil", 45, 1.6, pointsCost = 0, pointsReward = 15),
        Adventure("a4", "val", "El Archivo Perdido", "Media", 70, 2.8, pointsCost = 30, pointsReward = 40),
        Adventure("a5", "sev", "La Procesión", "Difícil", 100, 4.0, pointsCost = 60, pointsReward = 80)
    )

    fun getCities(query: String?): List<City> {
        if (query.isNullOrBlank()) return cities
        val q = normalize(query)
        return cities.filter { normalize(it.name).contains(q) || normalize(it.country).contains(q) }
    }

    fun getAdventuresByCity(cityId: String): List<Adventure> =
        adventures.filter { it.cityId == cityId && it.isActive }

    fun normalize(input: String): String {
        val normalized = Normalizer.normalize(input.lowercase(), Normalizer.Form.NFD)
        return normalized.replace(Regex("\\p{Mn}+"), "").trim()
    }
}
