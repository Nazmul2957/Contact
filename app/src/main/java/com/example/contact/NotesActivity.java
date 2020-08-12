package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contact.Adapter.NotesAdapter;
import com.example.contact.Database.DB.LocalDatabase;
import com.example.contact.Database.Dao.NoteDao;
import com.example.contact.Model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NotesActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    List<Note> notes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initilaizeData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NotesActivity.this,Note_save.class);
                startActivity(intent);
            }
        });
    }


    private void initilaizeData(){
        recyclerView=findViewById(R.id.notes_list_show);
        floatingActionButton=findViewById(R.id.note_save);
        LocalDatabase db=LocalDatabase.getReferences(NotesActivity.this);
        NoteDao noteDao=db.noteDao();
        try {
            notes=new getAllAsyncTask(noteDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(NotesActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        NotesAdapter notesAdapter=new NotesAdapter(notes);
        recyclerView.setAdapter(notesAdapter);
    }



    private static class getAllAsyncTask extends android.os.AsyncTask<Void, Void, List<Note>> {

        private NoteDao notedao;
        List<Note> notes;

        getAllAsyncTask(NoteDao dao) {
            notedao = dao;
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            return notedao.getAllnotes();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        initilaizeData();

    }
}