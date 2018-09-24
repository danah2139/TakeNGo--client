package com.example.dana.android5778_7109_3610_03.model.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        final String TAG="receiver";

        Toast.makeText(context," Car just released, list updated",Toast.LENGTH_SHORT).show();
        Log.d(TAG," Car just released, list updated");

    }
}
