package com.crazymaddoctor.urbanescape.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _canNavigate = MutableStateFlow(false)
    val canNavigate: StateFlow<Boolean> = _canNavigate

    fun onContinueClicked() {
        viewModelScope.launch {
            settingsRepository.setOnboardingSeen(true)
            _canNavigate.value = true
        }
    }

    fun consumeNavigation() {
        _canNavigate.value = false
    }
}
