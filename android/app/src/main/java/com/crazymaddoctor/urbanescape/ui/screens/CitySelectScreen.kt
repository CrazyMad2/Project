@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.crazymaddoctor.urbanescape.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crazymaddoctor.urbanescape.data.mock.MockData
import com.crazymaddoctor.urbanescape.di.ServiceLocator
import com.crazymaddoctor.urbanescape.ui.viewmodel.CitySelectViewModel
import com.crazymaddoctor.urbanescape.ui.viewmodel.CitySelectViewModelFactory

@Composable
fun CitySelectScreen(onCitySelected: (String) -> Unit) {
    val appContext = LocalContext.current.applicationContext
    val repo = remember { ServiceLocator.provideSettingsRepository(appContext) }
    val vm: CitySelectViewModel = viewModel(factory = CitySelectViewModelFactory(repo))

    var query by remember { mutableStateOf("") }
    val cities = remember(query) { MockData.getCities(query) }

    val navigateToCityId by vm.navigateToCityId.collectAsState()

    LaunchedEffect(navigateToCityId) {
        val cityId = navigateToCityId
        if (!cityId.isNullOrBlank()) {
            vm.consumeNavigation()
            onCitySelected(cityId)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Elige ciudad") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar ciudad") },
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cities) { city ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { vm.onCitySelected(city.id) }
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(city.name, style = MaterialTheme.typography.titleMedium)
                            Text(city.country, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
