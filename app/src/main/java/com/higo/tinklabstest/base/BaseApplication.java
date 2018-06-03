package com.higo.tinklabstest.base;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.higo.tinklabstest.data.gen.DaoMaster;
import com.higo.tinklabstest.data.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;

public class BaseApplication extends Application {
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private static final String DB_NAME = "tink_tabst.db";

    private static BaseApplication instance;


    /**
     * init TAG
     */
    private static String TAG = BaseApplication.class.getName();

    @Nullable
    private Activity mTopActivity;
    private final ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
            mTopActivity = activity;
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if (mTopActivity == activity) mTopActivity = null;
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO 开启greenDao的log
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        instance = this;
        setDatabase();
        registerActivityLifecycleCallbacks(mCallbacks);

    }

    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * init GreenDao
     */
    private void setDatabase() {
        devOpenHelper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
