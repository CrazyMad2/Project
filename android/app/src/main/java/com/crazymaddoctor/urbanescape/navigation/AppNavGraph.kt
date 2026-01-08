package com.crazymaddoctor.urbanescape.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crazymaddoctor.urbanescape.ui.screens.*

@Composable
fun AppNavGraph(startDestination: String = Routes.ONBOARDING) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onContinue = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onContinue = {
                    navController.navigate(Routes.CITY_SELECT) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.CITY_SELECT) {
            CitySelectScreen(
                onCitySelected = { cityId ->
                    navController.navigate("${Routes.ADVENTURE_LIST}/$cityId")
                }
            )
        }

        composable(
            route = "${Routes.ADVENTURE_LIST}/{${Routes.ARG_CITY_ID}}",
            arguments = listOf(navArgument(Routes.ARG_CITY_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString(Routes.ARG_CITY_ID) ?: return@composable
            AdventureListScreen(
                cityId = cityId,
                onAdventureSelected = { adventureId ->
                    navController.navigate("${Routes.ADVENTURE_DETAIL}/$cityId/$adventureId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.ADVENTURE_DETAIL}/{${Routes.ARG_CITY_ID}}/{${Routes.ARG_ADVENTURE_ID}}",
            arguments = listOf(
                navArgument(Routes.ARG_CITY_ID) { type = NavType.StringType },
                navArgument(Routes.ARG_ADVENTURE_ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString(Routes.ARG_CITY_ID) ?: return@composable
            val adventureId = backStackEntry.arguments?.getString(Routes.ARG_ADVENTURE_ID) ?: return@composable

            AdventureDetailScreen(
                cityId = cityId,
                adventureId = adventureId,
                onStart = { navController.navigate("${Routes.GAME}/$adventureId") },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.GAME}/{${Routes.ARG_ADVENTURE_ID}}",
            arguments = listOf(navArgument(Routes.ARG_ADVENTURE_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val adventureId = backStackEntry.arguments?.getString(Routes.ARG_ADVENTURE_ID) ?: return@composable
            GameScreen(
                adventureId = adventureId,
                onExit = { navController.popBackStack(Routes.CITY_SELECT, false) }
            )
        }
    }
}
