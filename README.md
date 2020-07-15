# CCC-M-crowing-rooster

___

## Tabla de contenido

- [Descripción](#descripción)
- [Usuarios de Prueba](#usuarios-de-prueba)
- [Funcionamiento de Usuarios](#funcionamiento-de-usuarios)
- [Development](#development)
- [Heroku](#heroku)

___

## Descripción

La app de CrowingRooster Baterías permite a los usuarios de android buscar la batería adecuada para su vehículo, leer sus especificaciones y negociar con vendedores a tiempo real a través de un chat para realizar pedidos directamente desde su móvil. Gracias a ella usted tiene la capacidad de comprender los componentes de la batería que necesita su vehículo. Los usuarios de CrowingRooster Baterías tienen acceso integral un catalogo con las baterías en inventarios, a su carrito de compras, y a un chat directo con el vendedor que gestiona tu pedido. Además puedes controlar el estado de todas los pedidos que has realizado. Todos los pedidos realizados desde CrowingRooster Baterias contienen la información detallada de tu orden y todos los pagos se realizan cuando llegue a la dirección que proporcionaste al vendedor. CrowingRooster Baterías es fácil de usar y efectiva en la información que proporciona al usuario.

- El IDE en que se trabajó en el código de la aplicación fue Android Studio.
- sdk mínimo requerido: 29

___

## Usuarios de Prueba

Credenciales para los usuarios de prueba(correo electrónico/contraseña):

### Usuario: comprador
- compradoruno@gmail.com/123456
- compradordos@gmail.com/123456

### Usuario: vendedor
- vendedoruno@gmail.com/123456
- vendedordos@gmail.com/123456

### Usuario: repartidor
- repartidoruno@gmail.com/123456
- repartidordos@gmail.com/123456
___

## Funcionamiento de usuarios

En esta sección se describe brevemente el funcionamiento básico de los usuarios.

### Usuario: vendedor
El usuario puede ir a las pantallas de su perfil, ver las ventas que ha realizado (pendientes y exitosas) y ver el chat de los clientes con los que esta negociando.

- En la pantalla de ventas, la pantalla permite al usuario regresar a un estado anterior ya sea presionando el botón atrás del dispositivo, o por medio del botón de navegación. Igualmente el usuario puede elegir entre visualizar sus ventas exitosas o pendientes. La pantalla respeta un patrón para visualizar las ventas exitosas como las pendientes, que es el mostrar la cantidad total de baterías, fecha en que se realizó la venta y el nombre del cliente. Así mismo, el usuario puede entrar a los detalles de venta presionando en la venta deseada.

- En la pantalla de detalles de venta, el usuario puede cerrar el diálogo de detalles (ventas exitosas) o regresar  a la pantalla anterior a través del botón “atrás” o presionando el botón atrás del dispositivo. En el caso de los detalles de una venta pendiente, el usuario solo puede realizar las opciones proporcionadas, que son: mandar un mensaje al comprador y confirmar la venta.

- En la pantalla de confirmación de venta pendiente, la pantalla solo permite el ingreso de valores congruentes a través de un “DatePicker”, “TimePicker” y una lista de opciones para el tipo de pago.

### Usuario: repartidor
El usuario vendedor solo cuenta con las pantallas para visualizar sus entregas pendientes y exitosas, y un botón para cerrar sesión.

- En la pantalla de entregas, La pantalla entrega permite al usuario cambiar entre entregas exitosas y pendientes y ver la lista de entregas, el usuario es capaz de hacer scroll hasta el fondo de la lista y regresar. El usuario además es capaz de regresar a la pantalla principal de la aplicación presionando el botón atrás de su dispositivo o pulsando el botón de regreso.

- En la pantalla de detalles de entrega, Al abrir la pantalla de diálogo correspondiente a una entrega exitosa el usuario puede ver los datos y cerrar el diálogo de detalles. En la pantalla de diálogo correspondiente una entrega pendiente además de tener las mismas características el usuario también puede confirmar que la entrega se realizó con éxito. En ambos casos el diálogo también puede cerrarse pulsando el botón atrás del dispositivo.

___

## Development
- Clonar el repositorio:

```
  $ git clone https://github.com/Slackoth/CCC-M-crowing-rooster.git
```

- En Android Studio ir a la pestaña "Project" y en "Gradle Scripts" ir al archivo "build.gradle(Module: app)" y en "dependencies" verificar que cuente con las siguientes implementaciones:

```groovy
    //room
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    // Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutine_version}"
    implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'

    //Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    //Hdodenhof
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    //MaterialDesignComponents
    implementation 'com.google.android.material:material:1.3.0-alpha01'

    //ViewModel
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'

    //RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.1.0'

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'

    //SmartMaterialSpinner
    implementation 'com.github.chivorns.androidx:smartmaterialspinner:1.2.1'

    //Timber
    implementation 'com.jakewharton.timber:timber:4.7.1'

    //recycleView
    //implementation 'com.android.support:recyclerview-v7:28.0.0'

    //circle buttom
    implementation 'com.github.markushi:circlebutton:1.1'

    //circle imageview for chat
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    // add the Firebase SDK for Google Analytics
    implementation 'com.google.firebase:firebase-analytics:17.2.2'

    //Firebase Auth
    implementation 'com.firebaseui:firebase-ui-auth:6.2.0'
    implementation 'com.google.firebase:firebase-database:19.1.0'
    implementation 'com.google.firebase:firebase-storage:19.1.1'
    implementation 'com.google.firebase:firebase-core:17.0.0'

    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.6.0'
    implementation 'com.squareup.retrofit2:converter-moshi:2.6.0'

    //Moshi
    implementation("com.squareup.moshi:moshi:1.9.3")
    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")

    //multidex
    implementation 'com.android.support:multidex:1.0.3'

    //grpupie
    implementation "com.xwray:groupie:2.8.0"

    //ThreeTenABP
    implementation 'com.jakewharton.threetenabp:threetenabp:1.2.4'
```

- En "App" en "manifest" abrir el archivo "AndroidManifest.xml" y verificar que cuente con los siguientes permisos:
```xml
      <!--Allows requests to the internet-->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.CALL_PHONE"/>
```

## Heroku
La app esta conectada a Heroku y todos sus datos ya se encuentran en línea en el siguiente enlace:

http://crowing-rooster-api.herokuapp.com/
