package com.example.pariay.wheredidiputit;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Catalog extends AppCompatActivity {

    DataBaseHelper mDataBaseHelper;

    public static final String TAG = "listViewActivity";

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        setTitle("Where Did I Put It?");

        mDataBaseHelper = new DataBaseHelper(this);

        listView = (ListView) findViewById(R.id.listView);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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

        Items.itemName.clear();
        Items.itemLocation.clear();
        while (data.moveToNext()) {
            // Iterate through our database(row by row) using a cursor
            Items.itemName.add(data.getString(1));
            Items.itemLocation.add(data.getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Items.itemName);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                toastMessage("you clicked on a listview");
                Cursor data = mDataBaseHelper.getItemID(name);
                int itemId = -1;
                String itemLocation = "";
                while (data.moveToNext()) {
                    itemId = data.getInt(0);
                    itemLocation = data.getString(2);
                }
                if (itemId > -1) {
                    Intent editScreenIntent = new Intent(Catalog.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id", itemId);
                    editScreenIntent.putExtra("name", name);
                    editScreenIntent.putExtra("location", itemLocation);
                    startActivity(editScreenIntent);
                } else {
                    toastMessage("No ID associated with that name");
                }
            }
        });
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
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //toastMessage("Back button pressed");
            Intent back = new Intent(Catalog.this, MainActivity.class);
            startActivity(back);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}
