package com.example.materialdesigndemo;

import android.app.Activity;
import android.os.Bundle;

import com.airbnb.deeplinkdispatch.DeepLinkDelegate;
import com.airbnb.deeplinkdispatch.DeepLinkHandler;

/**
 * Created by hezhiqiang on 16/9/6.
 */
@DeepLinkHandler
public class CustomDeepLinkHandler extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // perform your application-specific logic (eg.: logging, launch sign-in, etc.)
        // ...

        // Let DeepLinkDispatch handle the Intent and finish this Activity
        DeepLinkDelegate.dispatchFrom(this);
        finish();
    }
}
