package com.mx_satis.mxbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class product_list extends AppCompatActivity {
    DBHelper DB;
    Button home, product, product_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        DB = new DBHelper(this);
        home = (Button) findViewById(R.id.home);
        product = (Button) findViewById(R.id.product);
        product_listview = (Button) findViewById(R.id.product_listview);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(product_list.this, HomeActivity.class));
                finish();
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(product_list.this, ProductActivity.class));
                finish();
            }
        });

        product_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = kayitgetir();
                kayitgoster(cursor);
            }
        });
    }

    private String[] sutunlar={"barkod","kategori","marka","urunadi","miktari","alisfiyati","satisfiyati"};
    private Cursor kayitgetir()
    {
        SQLiteDatabase MyDB = DB.getWritableDatabase();
        Cursor okunanlar=MyDB.query("products",sutunlar,null,null,null,null,null);
        return okunanlar;
    }

    private void kayitgoster(Cursor goster)
    {
        StringBuilder builder=new StringBuilder();
        while (goster.moveToNext())
        {
            String barcode=goster.getString(goster.getColumnIndex("barkod"));
            String category=goster.getString(goster.getColumnIndex("kategori"));
            String brand=goster.getString(goster.getColumnIndex("marka"));
            String product_name=goster.getString(goster.getColumnIndex("urunadi"));
            String amount=goster.getString(goster.getColumnIndex("miktari"));
            String purchase_price=goster.getString(goster.getColumnIndex("alisfiyati"));
            String sale_price=goster.getString(goster.getColumnIndex("satisfiyati"));
            builder.append("barkod: ").append(barcode+"\n");
            builder.append("kategori: ").append(category+"\n");
            builder.append("marka: ").append(brand+"\n");
            builder.append("urunadi: ").append(product_name+"\n");
            builder.append("miktari: ").append(amount+"\n");
            builder.append("alisfiyati: ").append(purchase_price+"\n");
            builder.append("satisfiyati: ").append(sale_price+"\n");
            builder.append("-----------------").append("\n");
        }
        TextView text=(TextView) findViewById(R.id.textView_product);
        text.setText(builder);
    }
}