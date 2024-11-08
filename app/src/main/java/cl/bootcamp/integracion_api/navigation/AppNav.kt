package cl.bootcamp.integracion_api.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.bootcamp.integracion_api.view.HomeView
import cl.bootcamp.integracion_api.view.DetailView
import cl.bootcamp.integracion_api.viewmodel.NewsViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier
) {
    // Configuración de las pantallas de navegación
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(navController, viewModel)
        }
        composable("detail/{articleId}") { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId")
            Log.d("AppNavigation", "Recibido articleId en composable: $articleId")

            DetailView(
                articleId = articleId,
                articleName = null,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}