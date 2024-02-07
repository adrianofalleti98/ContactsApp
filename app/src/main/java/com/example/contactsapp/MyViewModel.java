package com.example.contactsapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.io.Closeable;
import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private MyRepository rep;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        rep = new MyRepository(application);
    }

    //metto i metodi add,delete e getAllContacts


    public LiveData<List<Contacts>> getAllContacts() {
        allContacts = rep.getAllContacts();
        return allContacts;
    }


    public void add(Contacts contact) {
        rep.addContact(contact);
    }

    public void delete(Contacts contact)
    {
        rep.deleteContact(contact);
    }
}
