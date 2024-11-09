package cl.bootcamp.integracion_api.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.bootcamp.integracion_api.model.Article
import coil.compose.rememberAsyncImagePainter

@Composable
fun NewsDetailCard(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(5.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Título de la noticia
            Text(
                text = article.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Imagen de la noticia
            article.urlToImage?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Imagen de la noticia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Autor de la noticia
            Text(
                text = "Autor: ${article.author ?: "Desconocido"}",
                fontSize = 18.sp,
                color = Color.DarkGray
            )


            Spacer(modifier = Modifier.height(8.dp))


            // Descripción de la noticia
            Text(
                "Descripción: ${article.description ?: "Sin descripción"}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}