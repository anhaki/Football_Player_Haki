package com.example.madun_haki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnTambah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_name);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama, nomor, klub;

                nama = etNama.getText().toString();
                nomor = etNomor.getText().toString();
                klub = etKlub.getText().toString();

                if(nama.trim().equals("") || nomor.trim().equals("") || klub.trim().equals("")){
                    if(nama.trim().equals("")){
                        etNama.setError("Nama player tidakboleh kosong!");
                    }
                    if(nomor.trim().equals("")){
                        etNama.setError("Nomor punggung tidak boleh kosong!");
                    }
                    if(klub.trim().equals("")){
                        etNama.setError("Klub tidak boleh kosong!");
                    }
                }
                else{
                    long eks = myDB.tambahPlayer(nama, nomor, klub);
                    if(eks == -1){
                        Toast.makeText(TambahActivity.this, "Gagal mrnambahkan data!", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(TambahActivity.this, "Berhasil menambahkan data!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }
}