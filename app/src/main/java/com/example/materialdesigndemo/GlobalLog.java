package com.example.materialdesigndemo;

import android.util.Log;

/**
 * Created by hezhiqiang on 16/6/27.
 */
public class GlobalLog {
    private static boolean DEBUG = true;

    public static void e(String tag,String message){
        if(DEBUG){
            Log.e(tag,message);
        }
    }

    public static void d(String tag,String message){
        if(DEBUG){
            Log.d(tag,message);
        }
    }

    public static void i(String tag,String message){
        if(DEBUG){
            Log.i(tag,message);
        }
    }

}
