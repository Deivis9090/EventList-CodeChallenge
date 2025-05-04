package com.example.listevents.model.room;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.listevents.adapter.EventAdapter;
import com.example.listevents.model.Event;

import java.util.List;

public class EventRepository {

    private static EventRepository INSTANCE;
    private final EventDao dao;
    private static Context context;
    private EventDatabase appdb;
    private LiveData<List<Event>> data;
    private LiveData<List<Event>> dataFavs;

    public static EventRepository getInstance(Application app) {
        if (INSTANCE == null) {
            INSTANCE = new EventRepository(app);
        }
        return INSTANCE;
    }

    private EventRepository(Application app) {
        context = app;
        appdb = EventDatabase.getInstance(app);
        dao = appdb.eventDao();
    }

    public LiveData<List<Event>> getAllEvents(){
        data = dao.getAllEvents();
        return data;
    }

    public LiveData<List<Event>> getAllFavoriteEvents(){
        dataFavs = dao.getAllFavoriteEvents();
        return dataFavs;
    }

    public void insert(Event d) {
        new InsertDataCardAsyncTask(dao).execute(d);
    }

    public void deleteAllData() {
        new DeleteAllDataAsyncTask(dao).execute();
    }

    public void updateFavorite(int id, int value, int pos, UpdateFavoriteCallback callback){
        new UpdateFavoriteAsyncTask(dao, callback).execute(id, value, pos);
    }

    private static class InsertDataCardAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao dc;

        private InsertDataCardAsyncTask(EventDao d) {
            this.dc = d;
        }

        @Override
        protected Void doInBackground(Event... events) {
            dc.insert(events[0]);
            return null;
        }
    }

    private static class DeleteAllDataAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao dc;

        private DeleteAllDataAsyncTask(EventDao d) {
            this.dc = d;
        }


        @Override
        protected Void doInBackground(Event... events) {
            try {
                dc.deleteAll();
            } catch (Exception w) {
            }
            return null;
        }
    }

    private static class UpdateFavoriteAsyncTask extends AsyncTask<Integer, Void, Integer> {
        private EventDao dao;
        private UpdateFavoriteCallback callback;

        UpdateFavoriteAsyncTask(EventDao dao, UpdateFavoriteCallback callback) {
            this.dao = dao;
            this.callback = callback;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int id = params[0];
            int value = params[1];
            int position = params[2];

            dao.updateFavorite(id, value);
            return position;
        }

        @Override
        protected void onPostExecute(Integer position) {
            if (callback != null) {
                callback.onUpdateComplete(position);
            }
        }
    }

    public interface UpdateFavoriteCallback {
        void onUpdateComplete(int position);
    }
}
