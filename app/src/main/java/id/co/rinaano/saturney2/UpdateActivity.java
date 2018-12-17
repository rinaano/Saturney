package id.co.rinaano.saturney2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private DBSaturney MyDatabase;
    private EditText NewNama, NewDetail, NewHarga;
    private String  getNewNama, getNewDetail, getNewHarga;
    private Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MyDatabase = new DBSaturney(getBaseContext());
        NewNama = findViewById(R.id.new_nama);
        NewDetail = findViewById(R.id.new_detail);
        NewHarga = findViewById(R.id.new_harga);
        Update = findViewById(R.id.new_data);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdateData();
                startActivity(new Intent(UpdateActivity.this, ViewData.class));
                finish();
            }
        });
    }

    private void setUpdateData(){
        getNewNama = NewNama.getText().toString();
        getNewDetail = NewDetail.getText().toString();
        getNewHarga = NewHarga.getText().toString();

        String GetNama = getIntent().getExtras().getString("SendNama");

        SQLiteDatabase database = MyDatabase.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBSaturney.MyColumns.Nama, getNewNama);
        values.put(DBSaturney.MyColumns.Detail, getNewDetail);
        values.put(DBSaturney.MyColumns.Harga, getNewHarga);

        String selection = DBSaturney.MyColumns.Nama + " LIKE ?";
        String[] selectionArgs = {GetNama};
        database.update(DBSaturney.MyColumns.NamaTabel, values, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();
    }
}