package com.crazymaddoctor.urbanescape.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository

class OnboardingViewModelFactory(
    private val repo: SettingsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OnboardingViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}

class CitySelectViewModelFactory(
    private val repo: SettingsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitySelectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CitySelectViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
