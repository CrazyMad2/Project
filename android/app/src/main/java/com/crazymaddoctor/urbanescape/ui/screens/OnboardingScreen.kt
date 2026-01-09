package com.crazymaddoctor.urbanescape.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crazymaddoctor.urbanescape.di.ServiceLocator
import com.crazymaddoctor.urbanescape.ui.viewmodel.OnboardingViewModel
import com.crazymaddoctor.urbanescape.ui.viewmodel.OnboardingViewModelFactory

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    val appContext = LocalContext.current.applicationContext
    val repo = remember { ServiceLocator.provideSettingsRepository(appContext) }
    val vm: OnboardingViewModel = viewModel(factory = OnboardingViewModelFactory(repo))

    val canNavigate by vm.canNavigate.collectAsState()

    LaunchedEffect(canNavigate) {
        if (canNavigate) {
            vm.consumeNavigation()
            onContinue()
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("UrbanEscape", style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(12.dp))
            Text(
                "Juega aventuras tipo escape room por la calle. " +
                    "Más adelante usaremos tu ubicación para validar pistas y puntos del mapa (sin pedir permisos aún)."
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { vm.onContinueClicked() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Continuar")
            }
        }
    }
}
