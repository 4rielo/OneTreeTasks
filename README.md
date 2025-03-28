This is a Technical Challenge app, in Kotlin multiplatform.

It displays a Tasks list, with a button to create a new task.

When creating a new task, in either iOS or Android, the location permission will be requested to register where was the task created.

Clicking on a task opens up the Task detail screen.

Task can be marked as completed via the checkbox button, or swiping right on the task list screen.

Task can be removed either by clicking on the trash can icon, in the Task Detail screen, or by swiping right on the Task List screen.


To run either iOS or Android app, just select the correspondant "Build Configuration", and click the play button.
To run the Desktop App, select the "MainKt" build configuration, and in the console type "./gradlew run".


Para el desarrollo del proyecto se emplearon las siguientes librerías externas:

Base de datos (persistencia de información): room   (https://developer.android.com/training/data-storage/room?hl=es-419)
Inyección de dependencias: koin  (https://insert-koin.io/)
Navegación: jetbrains compose navigation (https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html#type-safe-navigation)
Geo-localización: compass (https://github.com/jordond/compass)

Todas las librerías empleadas fueron en sus versiones más recientes a la hora de desarrollar el proyecto.

Las librerías están incluidas en el archivo build.gradle.kts de la aplicación (dentro del directorio /composeApp), y se incluyen por plataforma o como dependencias comunes.
Dentro de kotlin { sourcesSets se pueden ver
* androidMain.dependencies          //dependencias exclusivas para la plataforma Android
* iosMain.dependencies              //dependencias exclusivas para la plataforma iOS
* desktopMain.dependencies          //dependencias exclusivas para la plataforma desktop
* commonMain.dependencies           //dependencias globales de la aplicación.

El proyecto está generado, inicialmente, con el wizard de jetbrains (https://kmp.jetbrains.com/)

Estructura del proyecto:

./composeApp/src            -> código del proyecto en compose

./iosApp             -> carpeta base del proyecto de iOS (se puede abrir con XCode). Contiene archivos necesarios para buildear la app de iOS, como el pbxproj (información de build, nombre de app, versiones), y el info.plist (información de key/values de parámetros de la aplicación, como por ej, las keys necesarias para pedir permisos de acceso a la ubicación)


./composeApp/src/androidMain    -> código específico para plataforma Android
./composeApp/src/iosMain        -> código específico para plataforma iOS
./composeApp/src/desktopMain    -> código específico para plataforma desktop (probado en MacOS y Ubuntu - Linux, pero debería también poder correr en Windows)

./composeApp/src/commonMain     -> código en "común" de la aplicación. Aquí se encuentran los archivos de la aplicación en compose.

Dentro de commonMain, podemos encontrar la carpeta "comopseResources", que contiene el archivo /values/strings.xml
En éste archivo strings se encuentran los textos empleados en la aplicación. Generando distintos diccionarios string.xml se puede internacionalizar la aplicación, haciendo un archivo string por cada idioma deseado.

Luego, en commonMain/kotlin/org/ascarafia/onetreetasks se encuentra la estructura de proyecto.

El archivo "App.kt" es el punto de partida de la aplicación de compose, y dentro de cada plataforma, ésta es la vista principal que se carga.
Por ej, en iosMain/[...]/MainViewController.kt, que es el punto de partida de la aplicación de iOS, y aquí se carga la vista de App.
Lo mismo sucede en androidMain/[...]/MainActivity.kt

* application/ -> 
Todos los archivos referentes a la aplicación 
Aquí se encuentra la configuración de inyección de dependencias (di)

Helpers que responde a cuestiones puntuales de los sistemas operativos (helpers/UUIDGenerator). Ésta contiene una "expect class", la cual indica que cada plataforma la va a implementar de manera distinta, y el archivo para cada sistema se encuentra en el mismo directorio, pero partiendo de <platform>Main. 
Por ej, la implementación para Android del archivo ./composeApp/src/commonMain/kotlin/org/ascarafia/onetreetasks/application/helpers/UUIDGenerator se ecuentra en el archivo: ./composeApp/src/androidMain/kotlin/org/ascarafia/onetreetasks/application/helpers/UUIDGenerator.android.kt 
Éste archivo puntual, se encarga de obtener un UUID del dispositivo, sobre el cual generar un id random para la asignación de las tareas, pero la estructura de expect/actual class está desarrollada de la misma manera para otros archivos, como por ej, la base de datos, que tiene una implementación distinta para cada plataforma.

Volviendo a /application, también se encuentra la carpeta /location, que implementa todo lo relacionado con la ubicación.
Aquí también hay expect/actual clases, ya que tanto para Android como para iOS se emplea una librería de acceso a la ubicación, pero que no está disponible para desktop.

Además, aquí se podría incluir una sección de networking, donde se pueden agregar modelos de datos de request y response, y archivos relacionados con la obtención de información de internet mediante API calls, como service providers, interceptors (header interceptors, retry-interceptors, etc).


* data/ ->
Luego, se encuentra el directorio /data, donde se encuentran los archivos relacionados con la base de datos.


* domain/ ->
Por otro lado, está el directorio /domain, el cual contiene archivos que definen la funcionalidad de la aplicación, como por ejemplo, los modelos de datos empleados en la aplicación, o las interfaces para repositorios.
En éste directorio se puede incluir, además, archivos de useCases, como clases que contengan métodos genéricos para filtrar u ordenar lista de datos, procesamiento de información, etc.
Idealmente, los archivos contenidos en éste directorio no deberían depender de librerías externas ni plataformas, y deberían contener clases y funciones exclusivamente desarrolladas en Kotlin.


* ui/ ->
Finalmente, tenemos el directorio UI, donde se encuentran los archivos relacionados con las pantallas.
En éste caso, tenemos un directorio "theme", donde está almacenado el archivo de configuración del tema de la aplicación, o donde podrían ir archivos de constantes de colores empleados.
Y también encontramos el directorio "task_list". Ya que ésta es una aplicación sencilla, se agruparon todos los archivos de las distintas pantallas en el mismo directorio, junto con el archivo del ViewModel, y una carpeta Views para pequeñas vistas auxiliares, como por ej, el la vista del item de la lista.
Aquí se desearía separar en un directorio por cada pantalla empleada en la aplicación, junto con el correspondiente viewmodel si hiciese falta

