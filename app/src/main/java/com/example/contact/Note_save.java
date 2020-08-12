package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.contact.Database.DB.LocalDatabase;
import com.example.contact.Database.Dao.ContactDao;
import com.example.contact.Database.Dao.NoteDao;
import com.example.contact.Model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Note_save extends AppCompatActivity {
TextView title,description;
Button cancel,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_save);
        title=findViewById(R.id.note_title);
        description=findViewById(R.id.note_description);
        cancel=findViewById(R.id.cancle);
        save=findViewById(R.id.save_note);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           savedata();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void savedata(){
        if(!TextUtils.isEmpty(title.getText().toString())){
             if(!TextUtils.isEmpty(description.getText().toString())){
                 Note note=new Note();
                 note.setTitle(title.getText().toString());
                 note.setDescription(description.getText().toString());
                 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                 Date date = new Date();
                 note.setCreatedTime(formatter.format(date));
                 //inserting note----------
                 LocalDatabase db=LocalDatabase.getReferences(Note_save.this);
                 NoteDao noteDao=db.noteDao();
                 new InsertNote(noteDao).execute(note);
             finish();
             }
             else {
                 description.setError("enter your description");
                 description.requestFocus();
             }
        }
        else{
            title.setError("Enter title pls");
            title.requestFocus();
        }
    }
    private class InsertNote extends AsyncTask<Note,Void,Void > {

        private NoteDao noteDao;

        public InsertNote(NoteDao noteDao){
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            Log.i("data","hoho");
            return null;
        }
    }
}