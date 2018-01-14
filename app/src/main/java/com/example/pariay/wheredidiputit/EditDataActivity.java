package com.example.pariay.wheredidiputit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditDataActivity extends AppCompatActivity {

    public static final String TAG = "editActivity";

    private int selectedID;
    private String selectedName;
    private String selectedLocation;

    DataBaseHelper mDataBaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        final EditText itemName = (EditText) findViewById(R.id.nameEdit);
        final EditText itemLocation = (EditText) findViewById(R.id.locationEdit);

        Button btnSave = (Button) findViewById(R.id.save);

        Button btnDelete = (Button) findViewById(R.id.delete);

        mDataBaseHelper = new DataBaseHelper(this);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");
        selectedLocation = receivedIntent.getStringExtra("location");

        itemName.setHint(selectedName);
        itemLocation.setHint(selectedLocation);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = itemName.getText().toString();
                String locationText = itemLocation.getText().toString();

                if (nameText.length() == 0 && locationText.length() > 0) {
                    mDataBaseHelper.updateTable(null, locationText, selectedID);
                    toastMessage("Table updated");
                    onBackPressed();
                }
                else if (nameText.length() > 0 && locationText.length() > 0) {
                    mDataBaseHelper.updateTable(nameText, locationText, selectedID);
                    toastMessage("Table updated");
                    onBackPressed();
                }
                else if (nameText.length() > 0 && locationText.length() == 0) {
                    toastMessage("Please enter a location");
                }
                else {
                    toastMessage("No changes committed");
                    onBackPressed();
                }

                Log.d(TAG, "Whats going on");
                //onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBaseHelper.deleteRow(selectedID);
                toastMessage("Removed from database");
                onBackPressed();
            }
        });



    }
    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }
}
