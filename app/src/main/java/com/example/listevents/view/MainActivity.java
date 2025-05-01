package com.example.listevents.view;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements EventRepository.UpdateFavoriteCallback {

    private EventAdapter eventAdapter;
    private RecyclerView recyclerView;
    private EventViewModel eventViewModel;
    private SearchView searchView;
    private List<Event> allEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        recyclerView = findViewById(R.id.listViewEvents);
        searchView = findViewById(R.id.searchView);

        eventAdapter = new EventAdapter(new ArrayList<>(), this, this::onFavoriteClicked);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventAdapter);

        configureSearchView();

        eventViewModel.getAllEvents().observe(this, events -> {
            this.allEvents = events;
            eventAdapter.setData(events);
        });
    }

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
                filterEvents(newText.toLowerCase());
                return true;
            }
        });
    }

    private void filterEvents(String searchText) {
        if (searchText.isEmpty()) {
            eventAdapter.setData(allEvents);
        } else {
            List<Event> filteredList = new ArrayList<>();
            for (Event event : allEvents) {
                if (event.getName().toLowerCase().contains(searchText) ||
                        event.getDesc().toLowerCase().contains(searchText)) {
                    filteredList.add(event);
                }
            }
            eventAdapter.setData(filteredList);
        }
    }
}