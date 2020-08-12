package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contact.Adapter.ContactsAdapter;
import com.example.contact.Adapter.NotesAdapter;
import com.example.contact.Database.DB.LocalDatabase;
import com.example.contact.Database.Dao.ContactDao;
import com.example.contact.Database.Dao.NoteDao;
import com.example.contact.Model.Contact;
import com.example.contact.Model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContactActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    List<Contact> contacts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        recyclerView=findViewById(R.id.contact_list_show);
        floatingActionButton=findViewById(R.id.contact_save);
       initilaizeData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactActivity.this,Contact_save.class);
                startActivity(intent);
            }
        });
    }

    private void initilaizeData(){
        LocalDatabase db=LocalDatabase.getReferences(ContactActivity.this);
        ContactDao contactDao=db.contactDao();
        try {
            contacts=new getAllAsyncTask(contactDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(ContactActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        ContactsAdapter adapter=new ContactsAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }
    private static class getAllAsyncTask extends android.os.AsyncTask<Void, Void, List<Contact>> {

        private ContactDao contactDao;
        List<Note> notes;

        getAllAsyncTask(ContactDao dao) {
            contactDao = dao;
        }

        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return contactDao.getAllContact();
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        initilaizeData();

    }
}