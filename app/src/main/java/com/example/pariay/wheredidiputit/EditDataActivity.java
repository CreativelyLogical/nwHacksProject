package com.example.pariay.wheredidiputit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        TextView itemName = (TextView) findViewById(R.id.itemName);
        TextView itemLocation = (TextView) findViewById(R.id.itemLocation);

        itemName.setText("Hello");
        itemLocation.setText("World");
    }
}
