package id.co.rinaano.saturney2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    private DBSaturney MyDatabase;

    private RecyclerViewAdapter adapter;
    private ArrayList<DataFilter> dataList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        dataList = new ArrayList<>();
        MyDatabase = new DBSaturney(getBaseContext());

        RecyclerView recyclerView = findViewById(R.id.recycler);
        getData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(dataList);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
    }


    @SuppressLint("Recycle")
    protected void getData(){

        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBSaturney.MyColumns.NamaTabel,null);

        cursor.moveToFirst();

        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);

            dataList.add(new DataFilter(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);
        MenuItem searchIem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchIem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                nextText = nextText.toLowerCase();
                ArrayList<DataFilter> dataFilter = new ArrayList<>();
                for(DataFilter data : dataList){
                    String nama = data.getNama().toLowerCase();
                    if(nama.contains(nextText)){
                        dataFilter.add(data);
                    }
                }
                adapter.setFilter(dataFilter);
                return true;
            }
        });
        return true;
    }
}