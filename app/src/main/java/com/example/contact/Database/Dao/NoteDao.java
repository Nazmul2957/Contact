package com.example.contact.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.contact.Model.Note;

import java.util.List;
@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Query("DELETE FROM Note")
    void clear();

    @Query("SELECT * FROM Note")
    List<Note> getAllnotes();

    @Update
    void update(Note note);
}
