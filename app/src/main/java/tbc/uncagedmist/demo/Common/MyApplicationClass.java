package tbc.uncagedmist.demo.Common;

import android.app.Application;
import android.content.Context;

import cn.like.nightmodel.NightModelManager;

public class MyApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NightModelManager.getInstance().init(this);
    }
}