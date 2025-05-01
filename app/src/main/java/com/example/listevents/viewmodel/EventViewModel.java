package com.example.listevents.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.listevents.model.Event;
import com.example.listevents.model.room.EventDao;
import com.example.listevents.model.room.EventDatabase;
import com.example.listevents.model.room.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private EventRepository repository;
    private LiveData<List<Event>> data;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = EventRepository.getInstance(application);
    }

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

    public void updateFavorite(int id, int value, int pos, EventRepository.UpdateFavoriteCallback callback){
        repository.updateFavorite(id, value, pos, callback);
    }
}
