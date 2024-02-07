package com.example.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {



    ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
        ,R.layout.contact_list_item
        ,parent
        ,false));
    }

    @Override
    public int getItemCount() {
        if(contacts != null)
            return contacts.size();
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        //presenta un item o lo aggiorna, esattamente prende l'item alla posizione position
        Contacts c = contacts.get(position);
        holder.binding.setContact(c);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
//non metto come una volta i widget tipo textview o bottoni qui, ma uso il data binding per ottenerli
        ContactListItemBinding binding; //contactlistitembinding è una classe creata in automatico da android studio quando si crea una activty con il data binding
        public ContactViewHolder(@NonNull ContactListItemBinding b) {
            super(b.getRoot()); //serve una view a cui attaccare il viewHolder e noi lo attacchiamo alla radice della contactlistitem xml
            binding = b;
        }
    }
}
