package com.example.contactsapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.logging.LogRecord;

public class MyRepository {
    private final ContactDao dao;
    private ExecutorService executorService;
    private Handler handler;

    public MyRepository(Application app) {
        ContactDatabase db = ContactDatabase.getInstance(app.getApplicationContext());
        this.dao = db.getContactDAO();
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(contact);
            }
        });
    }

    public void deleteContact(Contacts contact)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(contact);
            }
        });
    }


    //getAllContacts non può usare run() perchè deve ritornare una lista di contatti e run invece non ritorna nulla

    public LiveData<List<Contacts>> getAllContacts() {
        return dao.getAllContacts();
    }

}
