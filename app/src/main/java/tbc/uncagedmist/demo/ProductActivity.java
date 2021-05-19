package tbc.uncagedmist.demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

import tbc.uncagedmist.demo.Adapter.MyAdapter;
import tbc.uncagedmist.demo.Adapter.ProductAdapter;
import tbc.uncagedmist.demo.Common.Common;
import tbc.uncagedmist.demo.Database.MyDatabase;
import tbc.uncagedmist.demo.Model.Product;
import tbc.uncagedmist.demo.Model.State;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(Common.CurrentStateName);

        recyclerView = findViewById(R.id.recycler_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new MyDatabase(this).getAllProductsByStateId(Common.CurrentStateId);

        while (cursor.moveToNext()) {
            Product product = new Product(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            productArrayList.add(product);
        }

        ProductAdapter adapter = new ProductAdapter(this,productArrayList);
        recyclerView.setAdapter(adapter);
    }
}