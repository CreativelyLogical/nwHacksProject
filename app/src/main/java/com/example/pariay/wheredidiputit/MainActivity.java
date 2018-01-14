package com.example.pariay.wheredidiputit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Where Did I Put It?");

        Button btnCatalog = (Button) findViewById(R.id.button);

        btnCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toastMessage("Catalog button pressed");
                Intent toCatalog = new Intent(MainActivity.this, Catalog.class);
                startActivity(toCatalog);
            }
        });
    }


    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

}
