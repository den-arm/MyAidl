package com.example.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
    IBinder ib = null;

    @Override
    public void onCreate() {
        super.onCreate();
        ib = new MyBinder(this.getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ib;
    }
}
