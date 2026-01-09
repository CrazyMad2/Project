package com.crazymaddoctor.urbanescape.domain.usecase

import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository
import com.crazymaddoctor.urbanescape.navigation.Routes
import kotlinx.coroutines.flow.first

class ResolveStartDestinationUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend fun resolve(): String {
        val seen = settingsRepository.onboardingSeen.first()
        if (!seen) return Routes.ONBOARDING

        val lastCity = settingsRepository.lastCityId.first()
        return if (lastCity.isNullOrBlank()) Routes.CITY_SELECT
        else "${Routes.ADVENTURE_LIST}/$lastCity"
    }
}
