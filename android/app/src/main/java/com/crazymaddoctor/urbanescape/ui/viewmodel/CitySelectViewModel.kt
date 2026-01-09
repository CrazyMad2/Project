package com.crazymaddoctor.urbanescape.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CitySelectViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _navigateToCityId = MutableStateFlow<String?>(null)
    val navigateToCityId: StateFlow<String?> = _navigateToCityId

    fun onCitySelected(cityId: String) {
        viewModelScope.launch {
            settingsRepository.setLastCityId(cityId)
            _navigateToCityId.value = cityId
        }
    }

    fun consumeNavigation() {
        _navigateToCityId.value = null
    }
}
