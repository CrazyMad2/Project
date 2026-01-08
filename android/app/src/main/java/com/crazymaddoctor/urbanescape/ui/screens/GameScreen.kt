@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.crazymaddoctor.urbanescape.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(adventureId: String, onExit: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Juego") },
                navigationIcon = { TextButton(onClick = onExit) { Text("Salir") } }
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
            Text("Aventura: $adventureId", style = MaterialTheme.typography.titleLarge)
            Text("Step engine coming next PR.")
        }
    }
}
