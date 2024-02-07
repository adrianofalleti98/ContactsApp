package com.example.contactsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ContactDatabase db;//il database
    private ArrayList<Contacts> cs = new ArrayList<>(); //i contatti
    private MyAdapter adapter; //l'adapter per popolare la recycler view
    private ActivityMainBinding amb;//data binding dell'activity main(contiene tutti i riferimenti al layout della nostra activity
    //e ci permette di settare le variabili messe nel layout
    private ActivityMainClickHandler clickHandler;//click handler(banale)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amb = DataBindingUtil.setContentView(this,R.layout.activity_main);
        amb.setClick(new ActivityMainClickHandler(this));

        //poi creiamo la recyclerview
        RecyclerView rcv = amb.recyclerView;
        //piccole cose che vanno sempre fatte con le recycler view
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setHasFixedSize(true);


//prendo il database o lo creo da 0
        db = ContactDatabase.getInstance(this);
        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);
        //pongo la nostra activty osservatrice del live data ritornata dal metodo getAllCOntacts in modo che lei possa fare certe cose
        //quando la lista ritornata da quel metodo cambia e se ne occupa da solo Android studio
        vm.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                /* N.B.: Questo metodo è fondamentale perchè all'apertura
                dell'applicazione viene chiamato subito in quanto i live data hanno un ciclo di
                vita e sicuramente questo metodo viene chiamato anche quando viene creato e
                pertanto aggiorniamo cs che poi diamo all'adapter che poi lo usa per recycler view   */
                cs.clear();
                for (Contacts contact:contacts) {
                    Log.v("TAGY",""+contact.getId());
                    cs.add(contact);
                }
                adapter.notifyDataSetChanged();
            }
        });
//creo l'adapter e gli metto la lista dei contatti con cui poi lui popolerà la recycler view
        adapter = new MyAdapter(cs);

        rcv.setAdapter(adapter);

        //handling of swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // If you swipe the item to the left
                Contacts c = cs.get(viewHolder.getAdapterPosition());

                vm.delete(c);
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(rcv);

    }
}