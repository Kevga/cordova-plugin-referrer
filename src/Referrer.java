package com.kevga.referrer;

//Cordova imports
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

//Android imports
import android.content.Context;
import android.app.Application;
import android.app.Activity;
import android.util.Log;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//Java Imports
import java.util.*;
import java.lang.Exception;
import org.json.JSONException;
import org.json.JSONArray;

public class Referrer extends CordovaPlugin {
    Context context;
    Application app;
    boolean enableLog;
    Activity activity;
    public static final String TAG = "com.kevga.referrer";
    public static final String KEY = "referrer";
    private static final String prefName = "referrer";

    //Constructor function.
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = this.cordova.getActivity().getApplicationContext();
        app = this.cordova.getActivity().getApplication();
        activity = this.cordova.getActivity();
        enableLog =  false;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if ("getReferrer".equals(action)) {
                getReferrer(callbackContext);
            } else if ("getInstallSource".equals(action)) {
                getInstallSource(callbackContext);
            } else if ("isInstalledByPlayStore".equals(action)) {
                isInstalledByPlayStore(callbackContext);
            } else {
                String error = "Unknown command: "+action;
                if (enableLog) {
                    Log.d(TAG, error);
                }
                callbackContext.error(error);
            }
            return true;
        } catch (Exception e){
            if(enableLog){
                e.printStackTrace();
                System.err.println(TAG+" execute Exception: " + e.getMessage());
            }
            callbackContext.error(e.getMessage());
            return false;
        }
    }

    @Override
    public void onPause(boolean multitasking) {

        if(enableLog)
            Log.d(TAG, "Activity on pause is called");

        super.onPause(multitasking);
    }

    @Override
    public void onResume(boolean multitasking) {

        if(enableLog)
            Log.d(TAG, "Activity on resume is called");

        super.onResume(multitasking);
    }

    public void getReferrer(final CallbackContext callbackContext) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
                    String referrer = sharedPreferences.getString(KEY, "");

                    //If no referrer was found, try defaultSharedPref
                    if (referrer == null || referrer.length() == 0){
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        referrer = sharedPreferences.getString(KEY, "");
                    }

                    if (referrer == null) {
                        referrer = "";
                    }

                    callbackContext.success(referrer);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void isInstalledByPlayStore(final CallbackContext callbackContext) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));
                    final String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                    callbackContext.success((installer != null && validInstallers.contains(installer)) ? "true" : "false");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getInstallSource(final CallbackContext callbackContext) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                    if (installer == null) {
                        installer = "";
                    }
                    callbackContext.success(installer);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

}