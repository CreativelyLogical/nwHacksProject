package com.example.pariay.wheredidiputit;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Catalog extends AppCompatActivity {

    DataBaseHelper mDataBaseHelper;

    public static final String TAG = "listViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        setTitle("Where Did I Put It?");

        mDataBaseHelper = new DataBaseHelper(this);

        ListView listView = (ListView) findViewById(R.id.listView);

//        Items.itemName.add("foo");
//        Items.itemName.add("bar");

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                Items.itemName);
        populateListView();
//        listView.setAdapter(arrayAdapter);
    }

    public void populateListView() {
        // returns all the rows of the database
        Cursor data = mDataBaseHelper.getData();

        while (data.moveToNext()) {
            // Iterate through our database(row by row) using a cursor
            Items.itemName.add(data.getString(1));
            Items.itemLocation.add(data.getString(2));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Items.itemName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                toastMessage("action item selected");
                Intent toAddMenu = new Intent(this, AddItems.class);
                startActivity(toAddMenu);
        }

        return super.onOptionsItemSelected(item);
    }


    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}
