package com.crazymaddoctor.urbanescape.data.settings

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.crazymaddoctor.urbanescape.domain.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "urbanescape_settings")

class SettingsDataStoreRepository(
    private val appContext: Context
) : SettingsRepository {

    private object Keys {
        val ONBOARDING_SEEN: Preferences.Key<Boolean> = booleanPreferencesKey("onboarding_seen")
        val LAST_CITY_ID: Preferences.Key<String> = stringPreferencesKey("last_city_id")
    }

    override val onboardingSeen: Flow<Boolean> =
        appContext.dataStore.data.map { prefs -> prefs[Keys.ONBOARDING_SEEN] ?: false }

    override val lastCityId: Flow<String?> =
        appContext.dataStore.data.map { prefs -> prefs[Keys.LAST_CITY_ID] }

    override suspend fun setOnboardingSeen(seen: Boolean) {
        appContext.dataStore.edit { it[Keys.ONBOARDING_SEEN] = seen }
    }

    override suspend fun setLastCityId(cityId: String?) {
        appContext.dataStore.edit { prefs ->
            if (cityId.isNullOrBlank()) prefs.remove(Keys.LAST_CITY_ID)
            else prefs[Keys.LAST_CITY_ID] = cityId
        }
    }
}
