package com.example.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
     Context contesto;
     Contacts contact;

     MyViewModel vm;

    public AddNewContactClickHandler(Context contesto, Contacts contact,MyViewModel vm1) {
        this.contesto = contesto;
        this.contact = contact;
        this.vm = vm1;
    }

    public void submit(View view)
    {
        if (contact.getName() == null || contact.getEmail() == null)
        {
            Toast.makeText(contesto, "Errore, non lasciare campi vuoti", Toast.LENGTH_SHORT).show();
        }
        else{
            Contacts c = new Contacts(contact.getName(),contact.getEmail());
            vm.add(c);
            Intent i = new Intent(contesto,MainActivity.class);
            contesto.startActivity(i);
        }
    }
}
