package com.liwenq.loveubymoment.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by liwenq on 12/31/2014.
 */
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PowerWakeTag");

        // acquire the lock
        wl.acquire();

        //Bundle extras = intent.getExtras();

        // start the checking service
        Intent serviceIntent = new Intent(context, MemorialDayCheckService.class);
        context.startService(serviceIntent);

        // release the lock
        wl.release();
    }

    public void SetAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, this.getClass());
//        intent.putExtra("SimpleMsg", "helloworld");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // make it happen at the exact time of every day
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 8);
//
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60*60*24, pendingIntent);
    }

    public void CancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, this.getClass());
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(sender);

    }
}






