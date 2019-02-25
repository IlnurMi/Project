package com.example.weatherapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Change extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd;
    EditText Name_city;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        btnAdd = (Button) findViewById(R.id.button_add);
        btnAdd.setOnClickListener(this);

        Name_city = (EditText) findViewById(R.id.add_city);

        dbHelper = new DBHelper(this);

    }

    public void onClick(View view){

        String name = Name_city.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (view.getId()) {

            case R.id.button_add:
                contentValues.put(DBHelper.KEY_NAME, name);
                database.insert(DBHelper.T_CITYS, null, contentValues);

                break;
        }
        dbHelper.close();


    }
}
