package com.crazymaddoctor.urbanescape.domain.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val onboardingSeen: Flow<Boolean>
    val lastCityId: Flow<String?>

    suspend fun setOnboardingSeen(seen: Boolean)
    suspend fun setLastCityId(cityId: String?)
}
