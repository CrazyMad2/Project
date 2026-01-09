package com.crazymaddoctor.urbanescape.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onContinue: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var info by remember { mutableStateOf("Demo UI (Firebase se integra en el siguiente PR).") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Iniciar sesión", style = MaterialTheme.typography.headlineMedium)
            Text(info, style = MaterialTheme.typography.bodyMedium)

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    info = "Login Email/Password pendiente (Firebase)."
                    onContinue()
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Entrar con Email") }

            OutlinedButton(
                onClick = {
                    info = "Google Sign-In pendiente (Firebase)."
                    onContinue()
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Entrar con Google") }

            TextButton(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Continuar como invitado") }
        }
    }
}
