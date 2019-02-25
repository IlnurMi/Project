package com.example.weatherapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView wheather_text;
    private TextView temp_text;
    private TextView wmain_text;
    private TextView wek_text;
    private TextView wek_text_1;
    private TextView wek_text_2;
    private ImageView add_img;
    private ImageView list_img;
    public static String NAME = "Kazan";
    public static String units = "metric";
    public static String LIST = "ru";
    public static String KEY = "fd6364786de34099eb9ee2121b1e39c1";

    static final private int KEY_CIT = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wheather_text = (TextView)findViewById(R.id.wheather_id);
        temp_text = (TextView)findViewById(R.id.temp_id);
        wmain_text = (TextView)findViewById(R.id.wmain_id);
        wek_text = (TextView)findViewById(R.id.week_id);
        wek_text_1 = (TextView)findViewById(R.id.week_id_1);
        wek_text_2 = (TextView)findViewById(R.id.week_id_2);

        add_img = (ImageView) findViewById(R.id.city_change);
        list_img = (ImageView) findViewById(R.id.city_list);





        MainFunction();
        MainListFunction();
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.city_change:
                Intent intent1 = new Intent(this,Change.class);
                startActivity(intent1);
                break;
            case R.id.city_list:
                Intent intent = new Intent(this,CityList.class);
                startActivityForResult(intent, KEY_CIT);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == KEY_CIT) {
            if (resultCode == RESULT_OK) {
                String thiefname = data.getStringExtra(CityList.THIEF);
                NAME = thiefname;

                MainFunction();
                MainListFunction();

            }
        }
    }

    public void MainFunction(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<PostName> call = jsonApi.getPostName(NAME,units,KEY);

        call.enqueue(new Callback<PostName>() {
            @Override
            public void onResponse(Call<PostName> call, Response<PostName> response) {
                /*if(!response.isSuccessful()){
                    wheather_text.setText("Code:" + response.code());
                    return;
                }*/
                wheather_text.setText(""+response.body().getCityName());
                wmain_text.setText("Today" + "\n" + response.body().getWeather().get(0).getMain() + "\n"+ (int)response.body().getMain().getTemp() + " C");

                 }

            @Override
            public void onFailure(Call<PostName> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Ошибка соединения",
                            Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void MainListFunction(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<PostList> callPostList = jsonApi.getPostList(NAME,units,KEY);
        callPostList.enqueue(new Callback<PostList>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList data = response.body();

                if (response.isSuccessful()) {
                }

                int k = 0;
                for (PostName day : data.getItems()) {
                    //if (day.getDate().get(Calendar.HOUR_OF_DAY) == 3) {
                        String date = String.format("%d.%d.%d %d:%d",
                                day.getDate().get(Calendar.DAY_OF_MONTH),
                                day.getDate().get(Calendar.WEEK_OF_MONTH),
                                day.getDate().get(Calendar.YEAR),
                                day.getDate().get(Calendar.HOUR_OF_DAY),
                                day.getDate().get(Calendar.MINUTE)
                       );

                        SimpleDateFormat formatDayOfWeek = new SimpleDateFormat("d,E");
                   String dayofWeek = formatDayOfWeek.format(day.getDate().getTime());

                   switch (k){
                       case 1:
                           wek_text.setText(dayofWeek + "\n"+ day.getWeather().get(0).getMain() +"\n" + (int)day.getMain().getTemp() + "C");
                           break;
                       case 9:
                           wek_text_1.setText(dayofWeek + "\n"+ day.getWeather().get(0).getMain() +"\n" + (int)day.getMain().getTemp() + "C");
                           break;
                       case 17:
                           wek_text_2.setText(dayofWeek + "\n"+ day.getWeather().get(0).getMain() +"\n" + (int)day.getMain().getTemp() + "C");
                           break;
                   }
                   k++;
                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
            }
        });


    }


}
