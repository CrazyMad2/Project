package com.crazymaddoctor.urbanescape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.crazymaddoctor.urbanescape.di.ServiceLocator
import com.crazymaddoctor.urbanescape.domain.usecase.ResolveStartDestinationUseCase
import com.crazymaddoctor.urbanescape.navigation.AppNavGraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appContext = LocalContext.current.applicationContext
            val settingsRepo = remember { ServiceLocator.provideSettingsRepository(appContext) }
            val resolver = remember { ResolveStartDestinationUseCase(settingsRepo) }

            var startDestination by remember { mutableStateOf<String?>(null) }

            LaunchedEffect(Unit) {
                startDestination = withContext(Dispatchers.IO) { resolver.resolve() }
            }

            Surface(color = MaterialTheme.colorScheme.background) {
                if (startDestination == null) {
                    androidx.compose.material3.Text("Cargando…")
                } else {
                    AppNavGraph(startDestination = startDestination!!)
                }
            }
        }
    }
}
