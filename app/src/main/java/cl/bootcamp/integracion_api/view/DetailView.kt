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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    articleId: String?,
    articleName: String?,
    navController: NavController,
    viewModel: NewsViewModel
) {
    // Recupera el artículo del ViewModel usando el `id` `
    val article = articleId?.let { viewModel.getArticleByIdOrName(it, null) }
    Log.d("DetailView", "Recibido articleId: $articleId, Artículo recibido: $article")



    // Si el artículo es nulo, muestra un mensaje
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
                modifier = Modifier.height(75.dp)
            )
        },
    ) {innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            // Imagen de la noticia
            val imagePainter = rememberAsyncImagePainter(article.urlToImage)
            Image(
                painter = imagePainter,
                contentDescription = "Imagen de la noticia",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Información del artículo
            Text("Autor: ${article.author ?: "Desconocido"}", fontSize = 18.sp, color = Color.DarkGray)
            Text("Título: ${article.title}", fontSize = 20.sp, color = Color.Black)
            Text("Descripción: ${article.description ?: "Sin descripción"}", fontSize = 18.sp, fontStyle = FontStyle.Italic)
            Text("URL: ${article.url}", fontSize = 16.sp, color = Color.Blue)

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para regresar
            Button(onClick = {
                navController.popBackStack()
                viewModel.cleanSelectedArticle()
            }) {
                Text("Volver")
            }
        }
    }
}