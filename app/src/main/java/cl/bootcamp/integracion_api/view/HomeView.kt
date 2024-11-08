package cl.bootcamp.integracion_api.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.integracion_api.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: NewsViewModel, modifier: Modifier = Modifier) {
    // Observa el flujo de titulares
    val articles = viewModel.topHeadlines.collectAsState().value
    val isLoading by viewModel.loading.collectAsState()
    val errorMessage by viewModel.error.collectAsState()

    var visibleItems by remember { mutableStateOf(10) } // Inicialmente, muestra solo 10 artículos
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Bienvenidos AppNews",
                        fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue
                ),
                modifier = Modifier.height(75.dp)
            )
        },
    ) {innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center) {
            when {
                isLoading -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = Color.Blue)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Cargando noticias...", color = Color.Blue)
                    }
                }
                errorMessage != null -> {
                    Text(text = errorMessage ?: "Error desconocido", color = Color.Red, modifier = Modifier.padding(16.dp))
                }
                articles.isEmpty() -> {
                    Text("No hay noticias disponibles", modifier = Modifier.padding(16.dp))
                }
                else -> {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {


                        items(articles) { article ->
                            Log.d("HomeView", "Article data: id=${article.id}, name=${article.name}, title=${article.title}")

                            val navRoute = when {
                                !article.id.isNullOrEmpty() -> "detail/${article.id}"
                                !article.name.isNullOrEmpty() -> "detail/?articleName=${article.name}"
                                else -> {
                                    val tempId = article.title.hashCode().toString()
                                    Log.w("HomeView", "Using temporary ID for article: $tempId")
                                    "detail/$tempId"
                                }
                            }

                            Log.d("HomeView", "Generated navRoute: $navRoute")

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = navRoute != null) {
                                        // Navega solo si `navRoute` no es null
                                        navRoute?.let { navController.navigate(it) }
                                    }
                                    .padding(8.dp)
                            )  {
                                Text(
                                    text = article.title ?: "Título no disponible",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Description: ${article.description ?: "Fuente desconocida"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    fontStyle = FontStyle.Italic
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Autor: ${article.author ?: "Desconocido"}",
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider(
                                thickness = 8.dp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                            )

                        }
                    }
                    // Detectar cuando el usuario llega al final de la lista para cargar más artículos
                    LaunchedEffect(listState) {
                        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                            .collect { lastVisibleIndex ->
                                if (lastVisibleIndex == visibleItems - 1 && visibleItems < articles.size) {
                                    visibleItems += 10 // Cargar 10 artículos adicionales
                                }
                            }
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) { viewModel.cleanSelectedArticle() }
}
