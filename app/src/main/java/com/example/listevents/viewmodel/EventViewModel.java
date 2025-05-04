package com.example.listevents.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.listevents.model.Event;
import com.example.listevents.model.room.EventDao;
import com.example.listevents.model.room.EventDatabase;
import com.example.listevents.model.room.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class EventViewModel extends AndroidViewModel {

    // Variables basicas para obtener la informacion de BD

    private EventRepository repository;
    private LiveData<List<Event>> data;
    private LiveData<List<Event>> dataFavs;

    // Mutables para identificar la lista a mostrar

    private MutableLiveData<Boolean> showFavorites = new MutableLiveData<>(false);
    public LiveData<Boolean> showFavoritesList = showFavorites;

    private final MutableLiveData<Boolean> _showOnlyFavorites = new MutableLiveData<>(false);
    public LiveData<Boolean> showOnlyFavorites = _showOnlyFavorites;

    private List<Event> _currentEvents = new ArrayList<>();

    // Instancia del ViewModel

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = EventRepository.getInstance(application);
    }

    // Funciones para el ViewModel (llamada de las vistas)

    public void insert(Event c) {
        repository.insert(c);
    }

    public void deleteAll() {
        repository.deleteAllData();
    }

    public LiveData<List<Event>> getAllEvents(){
        data = repository.getAllEvents();
        return data;
    }

    public LiveData<List<Event>> getAllFavoriteEvents(){
        dataFavs = repository.getAllFavoriteEvents();
        return dataFavs;
    }

    // Funciones para listas de Favoritos o todos los Eventos (segun el caso)

    public void showAllEvents() {
        _showOnlyFavorites.setValue(false);
    }

    public void showFavoriteEvents() {
        _showOnlyFavorites.setValue(true);
    }

    public LiveData<List<Event>> getEventsToDisplay() {
        return Transformations.switchMap(_showOnlyFavorites, showFavorites -> {
            return showFavorites ? getAllFavoriteEvents() : getAllEvents();
        });
    }

    public List<Event> getCurrentEvents() {
        return _currentEvents;
    }

    public void setCurrentEvents(List<Event> events) {
        _currentEvents = events;
    }

    // Funcion actualizar Favorito

    public void updateFavorite(int id, int value, int pos, EventRepository.UpdateFavoriteCallback callback){
        repository.updateFavorite(id, value, pos, callback);
    }
}
