package com.example.contact.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.Model.Contact;
import com.example.contact.Model.Note;
import com.example.contact.R;

import java.util.ArrayList;
import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.NotesViewHolder>{
 List<Contact> contacts=new ArrayList<>();


    public ContactsAdapter(List<Contact> contacts){
        this.contacts=contacts;

    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_show_view,parent,false);
        return new NotesViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {


        holder.name.setText(contacts.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView product_image,exit_btn,increment_btn,decrement_btn;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
              name=itemView.findViewById(R.id.contact_show);

        }


    }
}
