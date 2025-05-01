package com.example.listevents.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.listevents.model.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insert(Event c);

    @Update
    void update(Event c);

    @Delete
    void delete(Event c);

    @Query("DELETE FROM Event WHERE 1")
    void deleteAll();

    @Query("SELECT * FROM Event")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM Event")
    List<Event> getEvents();

    @Query("UPDATE Event SET favorite = :isFavorite WHERE id = :id")
    void updateFavorite(int id, int isFavorite);

    @Query("SELECT * FROM Event WHERE name LIKE :query OR `desc` LIKE :query")
    LiveData<List<Event>> searchEvents(String query);
}