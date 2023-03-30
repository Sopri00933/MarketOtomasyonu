package com.mx_satis.mxbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button exit,product,product_list;
    DBHelper DB;
    ListView users_list;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DB = new DBHelper(this);
        listItem = new ArrayList<>();
        users_list = findViewById(R.id.users_list);
        exit = (Button) findViewById(R.id.exit);
        product = (Button) findViewById(R.id.product);
        product_list = (Button) findViewById(R.id.product_list);

        viewData();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();

                Toast.makeText(HomeActivity.this, "Çıkış Başarılı", Toast.LENGTH_SHORT).show();
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProductActivity.class));
                finish();
            }
        });

        product_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, product_list.class));
                finish();
            }
        });
    }

    private void viewData() {
        Cursor cursor = DB.viewData();

        if(cursor.getCount()==0) {
            Toast.makeText(HomeActivity.this, "İşletme Adı Bulunamadı", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 ,listItem);
            users_list.setAdapter(adapter);
        }
    }
}