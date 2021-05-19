package tbc.uncagedmist.demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import cn.like.nightmodel.NightModelManager;
import tbc.uncagedmist.demo.Adapter.MyAdapter;
import tbc.uncagedmist.demo.Common.Common;
import tbc.uncagedmist.demo.Database.MyDatabase;
import tbc.uncagedmist.demo.Model.State;

public class MainActivity extends AppCompatActivity {

    public static final int ITEM_PER_AD = 4;
    public static final String BANNER_AD_ID = "";

    RecyclerView recyclerView;
    ArrayList<State> stateArrayList = new ArrayList<>();

    Button btnNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NightModelManager.getInstance().attach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(R.string.app_name);

        btnNight = findViewById(R.id.btnNight);
        recyclerView = findViewById(R.id.recycler_states);


        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNightModel();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        getDataItems();
//        geBannerItems();
//        loadBannerAds();



        MyAdapter adapter = new MyAdapter(stateArrayList,this);
        recyclerView.setAdapter(adapter);
    }


    private void getDataItems() {
        Cursor cursor = new MyDatabase(this).getAllStateData();

        while (cursor.moveToNext()) {
            State state = new State(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            stateArrayList.add(state);
        }
    }

    private void changeNightModel() {
        if (NightModelManager.getInstance().isCurrentNightModel(this)) {
            NightModelManager.getInstance().applyDayModel(this);
        } else {
            NightModelManager.getInstance().applyNightModel(this);
        }
    }

//    private void geBannerItems() {
//        for(int i = 0; i < stateArrayList.size(); i += ITEM_PER_AD)
//        {
//            AdView adView=new AdView(MainActivity.this);
//            adView.setAdSize(AdSize.BANNER);
//            adView.setAdUnitId(BANNER_AD_ID);
//            stateArrayList.add(i, adView); // 0 4 8 12...
//        }
//    }
//
//    private void loadBannerAds() {
//        for(int i = 0; i < stateArrayList.size(); i++)
//        {
//            State item = stateArrayList.get(i);
//            if(item instanceof AdView)
//            {
//                final AdView adView=(AdView) item;
//                adView.loadAd(new AdRequest.Builder().build());
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        NightModelManager.getInstance().detach(this);
        super.onDestroy();
    }
}