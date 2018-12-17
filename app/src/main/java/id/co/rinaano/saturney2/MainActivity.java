package id.co.rinaano.saturney2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Nama, Detail, Harga;
    private String setNama, setDetail, setHarga;
    private DBSaturney dbSaturney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button simpan = findViewById(R.id.save);
        Nama = findViewById(R.id.nama);
        Detail = findViewById(R.id.detail);
        Harga = findViewById(R.id.harga);

        dbSaturney = new DBSaturney(getBaseContext());

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });

        Button viewData = findViewById(R.id.readData);
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewData.class));
            }
        });
    }

    private void setData(){
        setNama = Nama.getText().toString();
        setDetail = Detail.getText().toString();
        setHarga = Harga.getText().toString();
    }

    private void saveData(){

        SQLiteDatabase create = dbSaturney.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBSaturney.MyColumns.Nama, setNama);
        values.put(DBSaturney.MyColumns.Detail, setDetail);
        values.put(DBSaturney.MyColumns.Harga, setHarga);

        create.insert(DBSaturney.MyColumns.NamaTabel, null, values);
    }

    private void clearData(){
        Nama.setText("");
        Detail.setText("");
        Harga.setText("");
    }

    public void btnMaps(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}