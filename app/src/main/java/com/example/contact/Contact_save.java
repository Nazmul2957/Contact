package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contact.Database.DB.LocalDatabase;
import com.example.contact.Database.Dao.ContactDao;
import com.example.contact.Database.Dao.NoteDao;
import com.example.contact.Model.Contact;
import com.example.contact.Model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact_save extends AppCompatActivity {
    EditText name,number;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_save);
        name=findViewById(R.id.login_email_et);
        number=findViewById(R.id.number_save);
        save=findViewById(R.id.contact_save_list);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savenumber();
            }
        });
    }
    public void savenumber(){
        if(!TextUtils.isEmpty(name.getText().toString())){
            if(!TextUtils.isEmpty(number.getText().toString())){
                Contact contact=new Contact();
                contact.setName(name.getText().toString());
                contact.setNumber(number.getText().toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = new Date();
                contact.setCreatedTime(formatter.format(date));
                LocalDatabase db=LocalDatabase.getReferences(Contact_save.this);
                ContactDao contactDao=db.contactDao();
                new InsertContact(contactDao).execute(contact);
               finish();
            }
            else{
                number.setError("Pleaase input your number");
                number.requestFocus();
            }
        }
        else{
           name.setError("Enter your name pls");
           name.requestFocus();
        }
    }
    private class InsertContact extends AsyncTask<Contact,Void,Void > {

        private ContactDao contactDao;

        public InsertContact(ContactDao contactDao){
            this.contactDao=contactDao;
        }


        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }
}