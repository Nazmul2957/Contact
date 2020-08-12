package com.example.contact.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contact.Model.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Query("DELETE FROM Contact")
    void clear();

    @Query("SELECT * FROM Contact")
    List<Contact> getAllContact();

    @Update
    void update(Contact contact);
}
