@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.crazymaddoctor.urbanescape.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.crazymaddoctor.urbanescape.data.mock.MockData

@Composable
fun AdventureListScreen(
    cityId: String,
    onAdventureSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    val adventures = MockData.getAdventuresByCity(cityId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Aventuras") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(adventures) { adv ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onAdventureSelected(adv.id) }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(adv.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text("Dificultad: ${adv.difficulty} · ${adv.durationMin} min · ${adv.distanceKm} km")
                        Text("Coste: ${adv.pointsCost} pts · Recompensa: ${adv.pointsReward} pts")
                    }
                }
            }
        }
    }
}
