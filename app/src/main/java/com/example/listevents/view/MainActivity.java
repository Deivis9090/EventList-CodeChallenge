package com.example.listevents.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listevents.R;
import com.example.listevents.adapter.EventAdapter;
import com.example.listevents.model.Event;
import com.example.listevents.model.room.EventDatabase;
import com.example.listevents.model.room.EventRepository;
import com.example.listevents.viewmodel.EventViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements EventRepository.UpdateFavoriteCallback {

    // Variables

    private EventAdapter eventAdapter;
    private RecyclerView recyclerView;
    private EventViewModel eventViewModel;
    private SearchView searchView;
    private TextView textViewFavorites;
    private TextView textViewAll;

    // OnCreate de la actividad principal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Elementos del layout

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        recyclerView = findViewById(R.id.listViewEvents);
        searchView = findViewById(R.id.searchView);
        textViewFavorites = findViewById(R.id.btnFavorites);
        textViewAll = findViewById(R.id.btnAll);

        // Intent para mandar informacion hacia DetailsActivity

        eventAdapter = new EventAdapter(new ArrayList<>(), this, this::onFavoriteClicked, item ->{
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("date", item.getDate());
            intent.putExtra("place",item.getPlace());
            intent.putExtra("desc", item.getDesc());
            intent.putExtra("image", item.getImage());
            startActivity(intent);
        });

        // Seteo del Adapter

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventAdapter);

        // Configuracion del SearchView (busqueda por nombre, descripcion)
        configureSearchView();

        // Observadores del Adapter para mandar listado de elementos

        textViewFavorites.setOnClickListener(v -> {
            eventViewModel.showFavoriteEvents();
        });

        textViewAll.setOnClickListener(v ->{
            eventViewModel.showAllEvents();
        });

        eventViewModel.getEventsToDisplay().observe(this, events -> {
            eventViewModel.setCurrentEvents(events);
            eventAdapter.setData(events);
            eventAdapter.notifyDataSetChanged();
        });
    }

    // Al hacer clic en Favoritos, modificar informacion en BD
    private void onFavoriteClicked(int position, Event event) {
        int newFavValue = (event.getFavorite() == 1) ? 0 : 1;
        eventViewModel.updateFavorite(event.getId(), newFavValue, position, this);
    }

    @Override
    public void onUpdateComplete(int position) {
        eventAdapter.notifyItemChanged(position);
    }

    private void configureSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterEvents(newText);
                return true;
            }
        });
    }

    // Filtrar lista por el SearchView

    private void filterEvents(String searchText) {
        List<Event> currentList = eventViewModel.getCurrentEvents();

        if (searchText.isEmpty()) {
            eventAdapter.setData(currentList);
        } else {
            List<Event> filteredList = new ArrayList<>();
            String query = searchText.toLowerCase();

            for (Event event : currentList) {
                String eventName = event.getName().toLowerCase();
                String eventDesc = event.getDesc().toLowerCase();

                if (eventName.contains(query) || eventDesc.contains(query)) {
                    filteredList.add(event);
                }
            }
            eventAdapter.setData(filteredList);
        }
    }
}