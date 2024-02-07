package com.example.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ActivityMainClickHandler {
    private Context context;

    public ActivityMainClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view)//fondamentale passare la view perchè sennò su che oggetto facciamo il click!?
    {
        Intent i = new Intent(view.getContext(),AddNewContactActivity.class);
        context.startActivity(i);
    }
}
