package com.example.tentativafrustada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        getUsers();
    }

    private void getUsers() {
        RetrofitService.getService().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<String> users = new ArrayList<String>();
                List<User> listaDeUsers = response.body();
                for (User user : listaDeUsers) {
                    users.add(user.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>( MainActivity.this,
                        android.R.layout.simple_spinner_item, users);
                spinner.setAdapter(adapter);

            }
            @Override
            public void onFailure (Call < List < User >> call, Throwable t){

            }
        });
    }
}
