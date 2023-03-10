package com.example.madun_haki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabTambah;
    private RecyclerView rvPlayer;
    private MyDatabaseHelper myDB;
    private AdapterFootballPlayer adPlayer;
    private ArrayList<String> arrNama, arrNomor, arrKlub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlayer = findViewById(R.id.rv_player);

        myDB = new MyDatabaseHelper(MainActivity.this);

        fabTambah = findViewById(R.id.fab_tambah);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilPlayer();
    }

    private void SQLiteToArrayList(){
        Cursor varCursor = myDB.bacaDataPlayer();
        if(varCursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
        else {
           while (varCursor.moveToNext()){
               arrNama.add(varCursor.getString(1));
               arrNomor.add(varCursor.getString(2));
               arrKlub.add(varCursor.getString(3));
           }
        }
    }

    private void tampilPlayer(){
        arrNama = new ArrayList<>();
        arrNomor = new ArrayList<>();
        arrKlub = new ArrayList<>();

        SQLiteToArrayList();
        adPlayer = new AdapterFootballPlayer(MainActivity.this, arrNama, arrNomor, arrKlub);
        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPlayer.setAdapter(adPlayer);
    }
}