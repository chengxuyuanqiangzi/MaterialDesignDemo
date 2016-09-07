package com.example.materialdesigndemo;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.airbnb.deeplinkdispatch.DeepLinkHandler;
import com.antfortune.freeline.FreelineCore;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by hezhiqiang on 16/7/13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //注册图片加载框架
        Fresco.initialize(this);
        //注册增量编译框架
        FreelineCore.init(this);

        IntentFilter intentFilter = new IntentFilter(DeepLinkHandler.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new DeepLinkReceiver(),intentFilter);
    }

    private class DeepLinkReceiver extends BroadcastReceiver{

        public final String TAG = DeepLinkReceiver.class.getSimpleName();

        @Override
        public void onReceive(Context context, Intent intent) {
            String deepLinkUri = intent.getStringExtra(DeepLinkHandler.EXTRA_URI);
            if(intent.getBooleanExtra(DeepLinkHandler.EXTRA_SUCCESSFUL,false)){
                Log.i(TAG, "Success deep linking: " + deepLinkUri);
            } else {
                String errorMessage = intent.getStringExtra(DeepLinkHandler.EXTRA_ERROR_MESSAGE);
                Log.e(TAG, "Error deep linking: " + deepLinkUri + " with error message +" + errorMessage);
            }
        }
    }
}
