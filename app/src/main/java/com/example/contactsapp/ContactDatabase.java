package com.example.contactsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Contacts.class,version = 4)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao getContactDAO();
    private static ContactDatabase dbinstance;

    //SINGLETON PATTERN, per essere sicuri di avere una sola istanza del db in tutta la app
    public static synchronized ContactDatabase getInstance(Context context)
    {
        if (dbinstance == null)
        {
            dbinstance = Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contacts_db").fallbackToDestructiveMigration().build();
        }
        return dbinstance;
    }


}
