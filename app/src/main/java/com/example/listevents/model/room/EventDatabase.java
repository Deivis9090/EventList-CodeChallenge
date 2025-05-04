package com.example.listevents.model.room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;

import com.example.listevents.model.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class}, version = 4)
public abstract class EventDatabase extends RoomDatabase {

    // Variables

    private static EventDatabase instance;
    public abstract EventDao eventDao();

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    // Instancias / Callbacks para BD

    public static synchronized EventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EventDatabase.class,
                            "event_database"
                    )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            executor.execute(() -> {
                EventDao dao = instance.eventDao();

                // Datos mockeados o hardcodeados
                // (se crean cada que se crea al BD o se instala la app)

                dao.insert(new Event("Natanael Cano",           "2025-04-22", "Palenque de la Feria", "nata_logo",     "Nathanahel Rubén Cano Monge es un cantante y compositor mexicano dedicado principalmente al género musical corridos tumbados, además de ser acreditado como precursor del mismo.",0));
                dao.insert(new Event("Billie Eilish",           "2025-04-22", "Foro de las Estrellas","billie_logo",   "Billie Eilish Pirate Baird OConnell, conocida simplemente como Billie Eilish, es una cantante, compositora y productora musical estadounidense.",0));
                dao.insert(new Event("Junior H",                "2025-04-23", "Foro de las Estrellas","junior_logo",   "Antonio Herrera Pérez, más conocido como Junior H, es un cantante y compositor mexicano. Se especializa en el subgénero de corridos tumbados.",0));
                dao.insert(new Event("Grupo Pesado",            "2025-04-24", "Palenque de la Feria", "pesado_logo",   "El Grupo Pesado es una agrupación de música regional mexicana especializada en el estilo norteño y fundada en 1993 en la ciudad de Monterrey, Nuevo León, México.",0));
                dao.insert(new Event("Grupo Firme",             "2025-04-25", "Foro de las Estrellas","grupo_logo",    "Grupo Firme es una agrupación de música regional mexicana creada por Jairo Corrales y Eduin Cazares en la ciudad de Tijuana, Baja California, en 2014.",0));
                dao.insert(new Event("Bullet For My Valentine", "2025-04-22", "Foro de las Estrellas","bfmv_logo",     "Bullet For My Valentine (abreviado BFMV) es una banda galesa de metalcore procedente de Bridgend, Gales.",0));
                dao.insert(new Event("Fuerza Regida",           "2025-04-26", "Palenque de la Feria", "regida_logo",   "Fuerza Regida es un grupo de música regional mexicana, formado en 2015. Han lanzado 11 álbumes y han sido nominados en varias ocasiones a premios",0));
                dao.insert(new Event("Bring Me The Horizon",    "2025-04-22", "Foro de las Estrellas","bmth_logo",     "Bring Me the Horizon (abreviado BMTH) es una banda británica de rock proveniente de Sheffield, Inglaterra. Se formó en 2003.",0));
                dao.insert(new Event("Spiritbox",               "2025-04-22", "Foro de las Estrellas","spiritbox_logo","Spiritbox es una banda canadiense de Metalcore originaria de Victoria, British Columbia. Formada por el dúo de marido y mujer, el guitarrista Mike Stringer y la cantante Courtney LaPlante, los cuáles crearon Spiritbox en 2017.",0));
            });
        }
    };
}
