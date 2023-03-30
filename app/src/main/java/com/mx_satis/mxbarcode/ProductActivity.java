package com.mx_satis.mxbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {
    DBHelper DB;
    EditText barcode, category, brand, product_name, amount, purchase_price, sale_price;
    Button home, product_add, product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        DB = new DBHelper(this);
        home = (Button) findViewById(R.id.home);
        product_add = (Button) findViewById(R.id.product_add);
        product_list = (Button) findViewById(R.id.product_list);
        barcode = (EditText) findViewById(R.id.barcode);
        category = (EditText) findViewById(R.id.category);
        brand = (EditText) findViewById(R.id.brand);
        product_name = (EditText) findViewById(R.id.product_name);
        amount = (EditText) findViewById(R.id.amount);
        purchase_price = (EditText) findViewById(R.id.purchase_price);
        sale_price = (EditText) findViewById(R.id.sale_price);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, HomeActivity.class));
                finish();
            }
        });

        product_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    kayitekle(barcode.getText().toString(),category.getText().toString(),brand.getText().toString(),product_name.getText().toString(),amount.getText().toString(),purchase_price.getText().toString(),sale_price.getText().toString());
                    Toast.makeText(ProductActivity.this, "Ürün Eklendi", Toast.LENGTH_SHORT).show();
                }
                finally {
                    DB.close();
                }
            }
        });

        product_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this, product_list.class));
                finish();
            }
        });
    }
    private void kayitekle(String barkod, String kategori, String marka, String urunadi, String miktari, String alisfiyati, String satisfiyati)
    {
        SQLiteDatabase MyDB=DB.getWritableDatabase();
        ContentValues veriler=new ContentValues();
        veriler.put("barcode", barkod);
        veriler.put("category", kategori);
        veriler.put("brand", marka);
        veriler.put("product_name", urunadi);
        veriler.put("amount", miktari);
        veriler.put("purchase_price", alisfiyati);
        veriler.put("sale_price", satisfiyati);

        MyDB.insert("product", null, veriler);
    }
}