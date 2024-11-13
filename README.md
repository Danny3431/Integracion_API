# 📰 NewsApp - Aplicación de Noticias
**NewsApp** es una aplicación móvil desarrollada en Android que permite a los usuarios acceder a titulares y artículos de noticias en tiempo real mediante la [News API](https://newsapi.org/). 

## 🚀 Características
- Ver titulares de noticias en tiempo real.
- Navegar entre categorías de noticias: principales, deportes, tecnología, y más.
- Consultar el detalle completo de cada artículo seleccionado.
- Retroceso fácil a la pantalla principal desde cualquier vista.
- Soporte de carga para imágenes y manejo de enlaces externos.

## 📸 Vista Previa
<p align="center">
  <img src="assets/screenshot1.png" alt="HomeView" width="200"/>
  <img src="assets/screenshot2.png" alt="DetailView" width="200"/>
</p>

## 🛠️ Estructura del Proyecto
```plaintext
app
└── src
    ├── main
    │   ├── java
    │   │   └── cl.bootcamp.integracion_api
    │   │       ├── model            # 📄 Modelo de datos (Article, NewsResponse)
    │   │       ├── view             # 🖼️ Pantallas principales (HomeView, DetailView)
    │   │       ├── viewmodel        # 🧠 ViewModel para manejo de estado (NewsViewModel)
    │   │       ├── repository       # 🗄️ NewsRepository para gestionar API
    │   │       └── network          # 🌐 Configuración de API con Retrofit (NewsApi, RetrofitInstance)
    │   └── res                      # 🎨 Recursos UI (layouts, icons, etc.)

```
## 📲 *API Utilizada*

Este proyecto usa la News API para obtener las noticias en tiempo real.
Para más información sobre la API y sus endpoints:

## Documentación News API
🔑 Clave API
Para utilizar la API, agrega tu propia clave API en el archivo de constantes:

## kotlin
```
object Constants {
    
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = BuildConfig.api_key //"TU_API_KEY_AQUI"
        const val COUNTRY = "us""
}
```
## 📂 Configuración del Proyecto

Clona el repositorio:

git clone https://github.com/Danny3431/Integracion_API.git
Configura tu API key en Constants.kt.
Ejecuta el proyecto en Android Studio.

## 🛠️ Tecnologías

- Kotlin: Lenguaje principal del proyecto.
- Jetpack Compose: Para crear una interfaz moderna y fluida.
- Retrofit: Manejo de llamadas HTTP para interactuar con la API.
-  Gson: Serialización y deserialización de datos JSON.
- Coil: Carga de imágenes desde URLs externas.

📋 Funcionalidades Detalladas

## 🏠 HomeView - Lista de Artículos

Vista principal que carga un listado de noticias obtenidas desde el endpoint top-headlines.

Cada artículo incluye:

- Título.
- Descripción.
- Autor y fuente.
- Navegación: Al seleccionar un artículo, el usuario es redirigido a la vista de detalles.

## 📑 DetailView - Detalles de Artículo

Vista secundaria para ver los detalles completos de un artículo específico.
Incluye:
- Título.
- Imagen del artículo.
- Autor, título y descripción completa.
- Botón de regreso a la vista principal.
- Navegación fácil con la opción de retroceso en la barra superior.

## 🖼️ Componentes Personalizados

NewsCard: Tarjeta para visualizar cada artículo en la lista con información clave.
TopAppBar: Barra de navegación personalizada con funcionalidad de retroceso.

## 🧑‍💻 Contribuciones

Las contribuciones son bienvenidas. Si deseas mejorar esta aplicación, sigue estos pasos:

Haz un fork del proyecto.
Crea una nueva rama: git checkout -b feature/nueva-funcionalidad.
Realiza tus cambios y haz commit: git commit -am 'Agregar nueva funcionalidad'.
Haz push a la rama: git push origin feature/nueva-funcionalidad.
Envía un Pull Request.

##  ✍️ Autores

Proyecto desarrollado como parte del Bootcamp Desarrollo de Aplicaciones Móviles Android Trainee v2.0 en Adalid.

¡Espero que disfrutes de la aplicación! 📲


Asegúrate de sustituir `TU_API_KEY_AQUI` con tu clave de API real

