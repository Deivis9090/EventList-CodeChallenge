# EVENT LIST
Proyecto de Android Nativo hecho con Java de listado de eventos

# ARQUITECTURA IMPLEMENTADA:
  MVVM (Model-View-ViewModel): Separa la lógica de negocio (ViewModel) de la UI (Activity/Fragment).
  LiveData: Para actualizaciones reactivas de la UI cuando cambian los datos.
  Room Database: Persistencia local de eventos.
  Repository Pattern: Centraliza el acceso a datos (API o BD local).

  ACTIVITY/FRAGMENT --- (Observa) --> VIEWMODEL --- (Usa --> REPOSITORY --- (Accede) --> ROOM DATABASE

# ORGANIZACION DEL PROYECTO:
  app/  
  ├── manifests/              # AndroidManifest.xml  
  ├── java/  
  │   ├── com.example.listevents/  
  │   │   ├── adapter/        # Adaptadores para RecyclerView
  │   │   ├── model/          # Clases de datos  
  │   │   │   ├── room/       # Configuración de Room (DAO, Database)
  │   │   ├── view/           # Activities y Fragments  
  │   │   └── viewmodel/      # ViewModels con LiveData
  │   ├── (androidTest)       
  │   └── (test)               
  ├── res/  
  │   ├── drawable/           # Íconos y recursos gráficos  
  │   ├── layout/             # XMLs de diseño (layouts)  
  │   ├── mipmap/             # Íconos de la app 
  │   ├── values/             # Strings, colores, estilos
  │   └── xml/                # Recursos XML adicionales

# PRINCIPIOS DE PROGRAMACION APLICADOS:
  SOLID:
    1. Single Responsibility Principle (SRP)
    ViewModel: La clase EventViewModel se encarga exclusivamente de la lógica de negocio (manejo de eventos y favoritos), sin mezclarse con la UI.
    Adapter: EventAdapter solo gestiona la representación visual de los datos en el RecyclerView.
    2. Open/Closed Principle (OCP)
    Extensibilidad: El uso de LiveData en EventViewModel permite observar cambios en los datos sin modificar la fuente original (abierto a extensión, cerrado a modificación).
    3. Liskov Substitution Principle (LSP)
    Interfaces limpias: La interfaz UpdateFavoriteCallback tiene un único método (onUpdateComplete), asegurando que cualquier implementación no rompa el contrato base.
    5. Dependency Inversion Principle (DIP)
    Inyección implícita: ViewModelProvider permite que MainActivity dependa de una abstracción (EventViewModel) en lugar de una implementación concreta.
  
  DRY:
  Reutilización efectiva
    1. LiveData Observers: La actualización de la lista de eventos (getEventsToDisplay().observe()) centraliza el flujo de datos en un solo punto, evitando repetición.
    2. Lambda en Adapter:
    eventAdapter = EventAdapter(..., item -> { 
      // Navegación reutilizable para todos los items 
    });
  Patrones consistentes
    1. SearchView: La lógica de filtrado (filterEvents) se aplica de manera uniforme para todos los eventos, sin duplicar código.

  KISS:
    Código intuitivo
      1. FullScreen: La configuración de pantalla completa se resuelve en 2 líneas claras:
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
      2. Listeners directos:
      textViewFavorites.setOnClickListener { eventViewModel.showFavoriteEvents() }
      Abstracciones justas
      3. ViewModel + LiveData: Simplifica el manejo de datos con un flujo reactivo y sin over-engineering.
      
# INSTALACION DE PROYECTO:
  1. Requisitos:
     - Android Studio (version Flamingo o superior).
     - Dispositivo / Emulador para ejecutar el proyecto.
  2. Pasos para Ejecucion:
     - Clonar el proyecto de GitHub (git clone )
     
