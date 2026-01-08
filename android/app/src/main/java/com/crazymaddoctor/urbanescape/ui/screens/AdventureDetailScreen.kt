@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.crazymaddoctor.urbanescape.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.crazymaddoctor.urbanescape.data.mock.MockData

@Composable
fun AdventureDetailScreen(
    cityId: String,
    adventureId: String,
    onStart: () -> Unit,
    onBack: () -> Unit
) {
    val adventure = MockData.getAdventuresByCity(cityId).firstOrNull { it.id == adventureId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (adventure == null) {
                Text("Aventura no encontrada")
                return@Column
            }

            Text(adventure.title, style = MaterialTheme.typography.headlineMedium)
            Text("Dificultad: ${adventure.difficulty}")
            Text("Duración: ${adventure.durationMin} min")
            Text("Distancia: ${adventure.distanceKm} km")
            Text("Coste: ${adventure.pointsCost} pts")
            Text("Recompensa: ${adventure.pointsReward} pts")

            Spacer(Modifier.height(12.dp))
            Button(onClick = onStart, modifier = Modifier.fillMaxWidth()) {
                Text("Empezar")
            }
            Text("Step engine coming next PR.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
