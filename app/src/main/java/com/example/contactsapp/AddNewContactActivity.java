package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contactsapp.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {


    private ActivityAddNewContactBinding binding;

    private Contacts contact;

    private MyViewModel vm1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        contact = new Contacts();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);

        vm1 = new ViewModelProvider(this).get(MyViewModel.class);
        binding.setClick(new AddNewContactClickHandler(this,contact,vm1));
        binding.setContact(contact);
    }
}