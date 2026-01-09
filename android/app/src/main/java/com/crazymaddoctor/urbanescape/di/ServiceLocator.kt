package com.crazymaddoctor.urbanescape.di

import android.content.Context
import com.crazymaddoctor.urbanescape.data.settings.SettingsDataStoreRepository
import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository

object ServiceLocator {
    @Volatile private var settingsRepo: SettingsRepository? = null

    fun provideSettingsRepository(context: Context): SettingsRepository {
        return settingsRepo ?: synchronized(this) {
            settingsRepo ?: SettingsDataStoreRepository(context.applicationContext).also { settingsRepo = it }
        }
    }
}
