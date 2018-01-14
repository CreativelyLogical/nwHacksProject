package com.example.pariay.wheredidiputit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Where Did I Put It?");

        Button btnCatalog = (Button) findViewById(R.id.button);

        Button deleteAll = (Button) findViewById(R.id.deleteAll);

        mDataBaseHelper = new DataBaseHelper(this);

        btnCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toastMessage("Catalog button pressed");
                Intent toCatalog = new Intent(MainActivity.this, Catalog.class);
                startActivity(toCatalog);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toastMessage("You're really gonna delete everything huh, savage");
                mDataBaseHelper.deleteAll();
                toastMessage("All entries in deleted");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            toastMessage("Back button pressed");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void toastMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

}
