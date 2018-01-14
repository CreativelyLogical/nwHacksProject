package com.example.pariay.wheredidiputit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditDataActivity extends AppCompatActivity {

    private int selectedID;
    private String selectedName;
    private String selectedLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        EditText itemName = (EditText) findViewById(R.id.nameEdit);
        EditText itemLocation = (EditText) findViewById(R.id.locationEdit);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");
        selectedLocation = receivedIntent.getStringExtra("location");

        itemName.setHint(selectedName);
        itemLocation.setHint(selectedLocation);


    }
}
