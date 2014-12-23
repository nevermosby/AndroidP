package com.liwenq.loveubymoment;

import android.app.ActionBar;;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;
import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.fragment.MemorialDayFragment;
import com.liwenq.loveubymoment.fragment.MomentNoteFragment;
import com.liwenq.loveubymoment.myLib.MemorialDaysUtil;

import java.util.List;

public class ViewPagerActivity extends FragmentActivity implements ActionBar.TabListener {

    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // check the memorial days
        checkMemorialDays();

        // fire the notification
        //createNotification();

        mViewPager=(ViewPager) findViewById(R.id.pager);
        
        final ActionBar actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        addTabs(actionBar);
        
        mViewPager.setAdapter(new myViewPagerAdapter(getSupportFragmentManager()));
        // set view pager change event listener
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(ViewPagerActivity.class.getName(),"onPageScrolled "+position+" "+positionOffset+" "+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                // viewpager --> actionBar
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE)
                    Log.d(ViewPagerActivity.class.getName(), "onPageScrollStateChanged scroll state idle " + state);
                if(state==ViewPager.SCROLL_STATE_DRAGGING)
                    Log.d(ViewPagerActivity.class.getName(),"onPageScrollStateChanged scroll state dragging "+state);
                if(state==ViewPager.SCROLL_STATE_SETTLING)
                    Log.d(ViewPagerActivity.class.getName(),"onPageScrollStateChanged scroll state settling "+state);
            }
        });
    }

    private void checkMemorialDays() {

        List<MomentNote> comingMemorialDays = MemorialDaysUtil.GetMemorialDayComing();
        int nid =1;
        if(comingMemorialDays!=null && comingMemorialDays.size()>0){
            for(MomentNote note : comingMemorialDays){
                createNotification(note, nid++);
            }
        }

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
                        .setContentTitle("Memorial Day is coming")
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

    private void addTabs(ActionBar actionBar) {
        ActionBar.Tab tab1=actionBar.newTab();
        tab1.setText("Memorial Day");
        tab1.setTabListener(this);

        ActionBar.Tab tab2=actionBar.newTab();
        tab2.setText("Moment Note");
        tab2.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // actionbar-->tab
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    class myViewPagerAdapter extends FragmentStatePagerAdapter {

        public myViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            if(position==0)
            {
                fragment=new MemorialDayFragment();
            }
            if(position==1)
            {
                fragment=new MomentNoteFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
