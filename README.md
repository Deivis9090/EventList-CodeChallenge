# EVENT LIST
Proyecto de Android Nativo hecho con Java de listado de objetos Event (String name, String date, String place, etc).

**ARQUITECTURA IMPLEMENTADA:**

  - MVVM (Model-View-ViewModel): Separa la lógica de negocio (ViewModel) de la UI (Activity/Fragment).
  - LiveData: Para actualizaciones reactivas de la UI cuando cambian los datos.
  - Room Database: Persistencia local de eventos.
  - Repository Pattern: Centraliza el acceso a datos (API o BD local).

  ACTIVITY/FRAGMENT --- (Observa) --> VIEWMODEL --- (Usa) --> REPOSITORY --- (Accede) --> ROOM DATABASE

**ORGANIZACION DEL PROYECTO:**
  
  ![image](https://github.com/user-attachments/assets/5a19248c-ab22-43e0-8c4f-535b4df047a9)

**PRINCIPIOS DE PROGRAMACION APLICADOS:**

  1. SOLID:
     - Single Responsibility Principle (SRP):
     ViewModel: La clase EventViewModel se encarga exclusivamente de la lógica de negocio (manejo de eventos y favoritos), sin mezclarse con la UI.
     Adapter: EventAdapter solo gestiona la representación visual de los datos en el RecyclerView.
     - Open/Closed Principle (OCP):
     Extensibilidad: El uso de LiveData en EventViewModel permite observar cambios en los datos sin modificar la fuente original (abierto a extensión, cerrado a modificación).
     - Liskov Substitution Principle (LSP):
     Interfaces limpias: La interfaz UpdateFavoriteCallback tiene un único método (onUpdateComplete), asegurando que cualquier implementación no rompa el contrato base.
     - Dependency Inversion Principle (DIP):
     Inyección implícita: ViewModelProvider permite que MainActivity dependa de una abstracción (EventViewModel) en lugar de una implementación concreta.
  2. DRY:
     - Reutilización efectiva:
       1. LiveData Observers: La actualización de la lista de eventos (getEventsToDisplay().observe()) centraliza el flujo de datos en un solo punto, evitando repetición.
       2. Lambda en Adapter:
    eventAdapter = EventAdapter(..., item -> { 
      // Navegación reutilizable para todos los items 
    });
     - Patrones consistentes:
       1. SearchView: La lógica de filtrado (filterEvents) se aplica de manera uniforme para todos los eventos, sin duplicar código.
  3. KISS:
     - Codigo Intuitivo:
       1. FullScreen: La configuración de pantalla completa se resuelve en 2 líneas claras:
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
       2. Listeners directos:
      textViewFavorites.setOnClickListener { eventViewModel.showFavoriteEvents() }
      Abstracciones justas
       3. ViewModel + LiveData: Simplifica el manejo de datos con un flujo reactivo y sin over-engineering.
      
**INSTALACION DE PROYECTO:**

  1. Requisitos:
     - Android Studio (version Flamingo o superior).
     - Dispositivo / Emulador para ejecutar el proyecto.
  2. Pasos para Ejecucion:
     - Clonar el proyecto de GitHub (git clone https://github.com/Deivis9090/EventList-CodeChallenge.git)
     - Abrir la aplicacion de Android Studio y abrir la carpeta del proyecto.
     - Antes de ejecutar, conectar el dispositivo al equipo y permitir en el telefono "Depuracion pos USB", en Opciones de Desarrollador.
     - Si no se tiene un dispositivo, se puede crear un Emulador para ejecutar la app, en Device Manager, "Add new device".
     - Ejecutar la app con "Run". 

**INSTALACION DE APLICACION:**

  1. Primero se descarga el archivo APK desde este enlace: https://drive.google.com/file/d/1RJu_D0GuSQ953LvKc_KmvjeBUq0mmlTO/view?usp=sharing
  2. Se va al archivo descargado y se permite instalar la app en el telefono (por cuestiones de seguridad, el telefono pregunta si se confia en la fuente).

**DEMO DE LA APP:**
  - Enlace a la demo: https://drive.google.com/file/d/1RlSsjbQzTQhez4vkDm-Vnl0M3nEJ4ece/view?usp=sharing

