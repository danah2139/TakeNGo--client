package com.example.dana.android5778_7109_3610_03.model.backend;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dana.android5778_7109_3610_03.model.datasource.DatabaseMySQL;

import static android.content.ContentValues.TAG;

public class MyService extends IntentService {

    final String TAG="update service";
    DB_manager manager= DBManagerFactory.getManager();
    public MyService(String s){super(s);}
    public MyService() {
        super("update service");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent intent1 = new Intent();
        intent1.setAction("INVITATION_SET");

        while (true) {
            try {
                Thread.sleep(10000);//check every 10 second
                Log.d(TAG, "thread run..");
                if (manager.checkClosedOrder())
                    sendBroadcast(intent1);
            } catch (Exception e) {
                Thread.currentThread().interrupt();

            }

        }
    }

}
