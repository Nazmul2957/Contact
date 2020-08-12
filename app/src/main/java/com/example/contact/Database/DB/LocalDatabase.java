package com.example.contact.Database.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contact.Database.Dao.ContactDao;
import com.example.contact.Database.Dao.NoteDao;
import com.example.contact.Model.Contact;
import com.example.contact.Model.Note;

@Database(entities = {Contact.class, Note.class},version =2,exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase databaseRef;
    public abstract ContactDao contactDao();
    public abstract NoteDao noteDao();


    public static LocalDatabase getReferences(@NonNull Context context){
        if (databaseRef==null){
            synchronized (LocalDatabase.class){
                if (databaseRef == null ){
                    databaseRef= Room.databaseBuilder(context,LocalDatabase.class,"ContactApp").fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseRef;
    }
}
