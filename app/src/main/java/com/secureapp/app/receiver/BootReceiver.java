package com.secureapp.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.secureapp.app.service.PersistentService;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "Boot event received: " + action);

        if (Intent.ACTION_BOOT_COMPLETED.equals(action) ||
            Intent.ACTION_REBOOT.equals(action) ||
            "android.intent.action.QUICKBOOT_POWERON".equals(action)) {

            Intent serviceIntent = new Intent(context, PersistentService.class);
            serviceIntent.setPackage(context.getPackageName());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            } else {
                context.startService(serviceIntent);
            }

            Log.d(TAG, "Service restarted after boot");
        }
    }
}
