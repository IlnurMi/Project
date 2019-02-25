package com.example.weatherapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityList extends AppCompatActivity {
    LinearLayout layout;
    ScrollView nes;
    LinearLayout man_layout;
    public List<String> city;
    public final static String THIEF = "THIEF";

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        city = new ArrayList<String>();

        dbHelper = new DBHelper(this);


        layout = findViewById(R.id.base_layout);
        man_layout = findViewById(R.id.main_layout);
        nes = findViewById(R.id.nes);


        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.T_CITYS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            do {
                city.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        }

        cursor.close();


        for(String name : city){
            View view = getLayoutInflater().inflate(R.layout.shablon,null);
            TextView text = view.findViewById(R.id.shablon_city_name);
            text.setText(name);
            man_layout.addView(view);
        }

    }

    public void onClick(View view){
        Intent answerIntent = new Intent();
        TextView text = view.findViewById(R.id.shablon_city_name);
        answerIntent.putExtra(THIEF,text.getText().toString());
        Log.d("mLog",text.getText().toString());
        setResult(RESULT_OK, answerIntent);
        finish();
    }



}

