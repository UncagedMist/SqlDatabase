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
import tbc.uncagedmist.demo.Common.Common;
import tbc.uncagedmist.demo.Database.MyDatabase;
import tbc.uncagedmist.demo.Model.State;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<State> stateArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        recyclerView = findViewById(R.id.recycler_states);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        Cursor cursor = new MyDatabase(this).getAllStateData();

        while (cursor.moveToNext()) {
            State state = new State(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            stateArrayList.add(state);
        }

        MyAdapter adapter = new MyAdapter(stateArrayList,this);
        recyclerView.setAdapter(adapter);
    }
}