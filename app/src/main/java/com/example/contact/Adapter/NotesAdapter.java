package com.example.contact.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.Model.Note;
import com.example.contact.R;

import java.util.ArrayList;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
 List<Note> notes=new ArrayList<>();


    public NotesAdapter(List<Note> notes){
        this.notes=notes;

    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_save_list_show,parent,false);
        return new NotesViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {


        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView title,description;
        ImageView product_image,exit_btn,increment_btn,decrement_btn;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
              title=itemView.findViewById(R.id.note_list_tilte);
              description=itemView.findViewById(R.id.note_description);

        }


    }
}
