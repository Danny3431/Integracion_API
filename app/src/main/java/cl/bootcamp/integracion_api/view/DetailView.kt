package cl.bootcamp.integracion_api.view



import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.integracion_api.viewmodel.NewsViewModel
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontStyle
import cl.bootcamp.integracion_api.components.NewsDetailCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    articleId: String?,
    articleName: String?,
    navController: NavController,
    viewModel: NewsViewModel
) {
    val article = viewModel.getArticleByIdOrName(articleId, articleName)

    if (article == null) {
        Text("Noticia no encontrada")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Detalle Noticia",
                        fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
                modifier = Modifier.height(75.dp)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Llamada a la tarjeta de detalles
            NewsDetailCard(article)

            Spacer(modifier = Modifier.height(16.dp))

           /* // Botón adicional de regresar, si es necesario
            Button(onClick = {
                navController.popBackStack()
                viewModel.cleanSelectedArticle()
            }) {
                Text("Volver")
            }*/
        }
    }
}