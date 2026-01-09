package com.crazymaddoctor.urbanescape

import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository
import com.crazymaddoctor.urbanescape.domain.usecase.ResolveStartDestinationUseCase
import com.crazymaddoctor.urbanescape.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

private class FakeSettingsRepository(
    onboardingSeenInitial: Boolean,
    lastCityIdInitial: String?
) : SettingsRepository {
    private val _onboardingSeen = MutableStateFlow(onboardingSeenInitial)
    private val _lastCityId = MutableStateFlow(lastCityIdInitial)

    override val onboardingSeen: Flow<Boolean> = _onboardingSeen.asStateFlow()
    override val lastCityId: Flow<String?> = _lastCityId.asStateFlow()

    override suspend fun setOnboardingSeen(seen: Boolean) { _onboardingSeen.value = seen }
    override suspend fun setLastCityId(cityId: String?) { _lastCityId.value = cityId }
}

class ResolveStartDestinationUseCaseTest {

    @Test
    fun whenOnboardingNotSeen_startIsOnboarding() = runBlocking {
        val repo = FakeSettingsRepository(onboardingSeenInitial = false, lastCityIdInitial = "bcn")
        val useCase = ResolveStartDestinationUseCase(repo)
        assertEquals(Routes.ONBOARDING, useCase.resolve())
    }

    @Test
    fun whenOnboardingSeen_andLastCityExists_startIsAdventureListForCity() = runBlocking {
        val repo = FakeSettingsRepository(onboardingSeenInitial = true, lastCityIdInitial = "mad")
        val useCase = ResolveStartDestinationUseCase(repo)
        assertEquals("${Routes.ADVENTURE_LIST}/mad", useCase.resolve())
    }
}
