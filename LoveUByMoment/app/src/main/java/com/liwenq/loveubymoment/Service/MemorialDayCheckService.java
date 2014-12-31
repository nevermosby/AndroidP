package com.liwenq.loveubymoment.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.liwenq.loveubymoment.DayDetailActivity;
import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.R;
import com.liwenq.loveubymoment.myLib.MemorialDaysUtil;

import java.util.List;

/**
 * Created by liwenq on 12/31/2014.
 */
public class MemorialDayCheckService extends Service {

    private static String TAG="MemorialDayCheckService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Checking service is created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "Checking service is about to start...");
        List<MomentNote> comingMemorialDays = MemorialDaysUtil.GetMemorialDayComing();
        int nid =1;
        if(comingMemorialDays!=null && comingMemorialDays.size()>0){
            for(MomentNote note : comingMemorialDays){
                createNotification(note, nid++);
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void createNotification(MomentNote note, int nId) {
    /*
    set up a notification
    * */
        // prepare intent which is triggered if the notification is selected
        Intent intent = new Intent(this, DayDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra("MOMENTDAY", gson.toJson(note));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // build notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("It is coming: " + note.GetBody())
                .setContentText(note.GetTitle())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Sets an ID for the notification
        // int mNotificationId = nId;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(nId, mBuilder.build());
    }

}
