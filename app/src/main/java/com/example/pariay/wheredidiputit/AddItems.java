package com.example.pariay.wheredidiputit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        final EditText itemToAdd = (EditText) findViewById(R.id.itemToAdd);

        final EditText itemLocation = (EditText) findViewById(R.id.itemLocation);

        Button addItem = (Button) findViewById(R.id.addItem);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemToAdd.getText().toString();
                String location = itemLocation.getText().toString();

                if (item.length() == 0) {
                    toastMessage("Please enter an Item name");
                }
                else if (location.length() == 0) {
                    toastMessage("Please enter a location for the item");
                }
                else if (location.length() == 0 && item.length() == 0) {
                    toastMessage("Please enter something");
                }
                else {
                    Items.itemName.add(item);
                    Items.itemLocation.add(location);
                    toastMessage("Item added");
                    Intent backToCatalog = new Intent(AddItems.this, Catalog.class);
                    startActivity(backToCatalog);
                }
            }
        });


    }

    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}