package com.example.myaidl;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class MyBinder extends Mp3PlayerInterface.Stub {
    private MediaPlayer mPlayer = null;
    private Context ctx;
    String Status = null;

    public MyBinder(Context cx){
        ctx = cx;
    }

    public void player() {
        if(mPlayer != null) return;
        Status = "Playing MP3";
        mPlayer = MediaPlayer.create(ctx,R.raw.taomagan);
        mPlayer.start();
//        try{
//            mPlayer.start();
//        }catch(Exception e){
//            Log.e("StartPlay", "error:" + e.getMessage(),e);
//        }
    }

    public void stop() {
        if(mPlayer != null){
            Status = "Stop MP3";
            mPlayer.stop();
            mPlayer.release();
//            try{
//                mPlayer.stop();
//                mPlayer.release();
//            }catch(Exception e){
//                Log.e("StopPlay", "error:" + e.getMessage(),e);
//            }
            mPlayer = null;
        }
    }

    public String getStatus(){
        return Status;
    }
}
