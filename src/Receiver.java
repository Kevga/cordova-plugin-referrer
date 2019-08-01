package com.kevga.referrer;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Receiver extends BroadcastReceiver {
    private static final String KEY = "referrer";
    private static final String prefName = "referrer";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String referrerString = extras.getString("referrer");
            if (referrerString != null) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

                Editor edit = sharedPreferences.edit();
                edit.putString(KEY, referrerString);
                edit.commit();
            }
        }
    }
}