package com.example.myaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Mp3PlayerInterface pProxy = null;
    private boolean isConnSuccess;
    private Button btn,btn2,btn3;
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        tv = findViewById(R.id.textView);

        //startService(...) //可以省略，调用bindService时，如果没有启动，系统会自动启动服务
        Intent intent = new Intent("com.example.myaidl.REMOTE_SERVICE");
        intent.setPackage("com.example.myaidl");//android 5.0后需要显示声明
        isConnSuccess = bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder ibinder) {
            pProxy = Mp3PlayerInterface.Stub.asInterface(ibinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void onClick(View v) {
        if(isConnSuccess == false) finish();
        switch (v.getId()) {
            case R.id.button:
                try {
                    pProxy.player();
                    tv.setText(pProxy.getStatus());
                } catch (RemoteException e) {
                    Log.e("StartPlay", "error:" + e.getMessage(),e);
                }

                break;
            case R.id.button2:
                try {
                    pProxy.stop();
                    tv.setText(pProxy.getStatus());
                } catch (RemoteException e) {
                    Log.e("Stop", "error:" + e.getMessage(),e);
                }
                break;
            case R.id.button3:
                finish();
                break;

        }
    }
}