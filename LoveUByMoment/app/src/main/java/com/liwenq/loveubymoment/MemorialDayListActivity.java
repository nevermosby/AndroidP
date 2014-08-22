package com.liwenq.loveubymoment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.liwenq.loveubymoment.myLib.TimeDiff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * An activity representing a list of MemorialDays. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MemorialDayDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MemorialDayListFragment} and the item details
 * (if present) is a {@link MemorialDayDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link MemorialDayListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class MemorialDayListActivity extends Activity
        implements MemorialDayListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private static final String TAG = "loveu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorialday_list);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.d("loveu", "current datetime: " + currentDateTimeString);
        Date metTime =null;
        Date now = new Date();
        try {
           metTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2011/09/25 19:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long[] diff = TimeDiff.getTimeDifference(metTime, now);
        Log.d(TAG, "Time difference is " +diff[0]+" day(s),"+ diff[1] +" hour(s)," + diff[2] + " minute(s),"+diff[3] +" second(s) and "+ diff[4]+" millisecond(s)");
        if (findViewById(R.id.memorialday_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((MemorialDayListFragment) getFragmentManager()
                    .findFragmentById(R.id.memorialday_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link MemorialDayListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(MemorialDayDetailFragment.ARG_ITEM_ID, id);
            MemorialDayDetailFragment fragment = new MemorialDayDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.memorialday_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, MemorialDayDetailActivity.class);
            detailIntent.putExtra(MemorialDayDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
