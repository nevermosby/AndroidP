package com.liwenq.loveubymoment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.liwenq.loveubymoment.DayDetailActivity;
import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.MyAdapter.NoteListAdapter;
import com.liwenq.loveubymoment.R;
import com.liwenq.loveubymoment.myLib.MemorialDaysUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenq on 9/6/2014.
 */
public class MemorialDayFragment extends ListFragment {
    private List<MomentNote> momentNoteList = new ArrayList<MomentNote>();
    Context context;
    private static String TAG = "MemorialDayFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_listview_fragment, null,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // populate data
        momentNoteList.addAll(MemorialDaysUtil.GetMemorialDays());

        // initialize list view
        PopulateListView();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


        //TODO: clear the list view

        // navigate to new activity

        // fetch the detail information
        MomentNote clickedDetail = (MomentNote)l.getItemAtPosition(position);
        Log.d(TAG, "Clicked item: " + clickedDetail.GetTitle() + ", " + clickedDetail.GetBody());

        Gson gson = new Gson();

        Intent dayDetailIntent = new Intent(getActivity(), DayDetailActivity.class);
        dayDetailIntent.putExtra("MOMENTDAY", gson.toJson(clickedDetail));
        startActivity(dayDetailIntent);

        /*
        //super.onListItemClick(l, v, position, id);
        MomentNote clickedDetail = (MomentNote)l.getItemAtPosition(position);
        Log.d(TAG, "Clicked item: " + clickedDetail.GetTitle() + ", " + clickedDetail.GetBody());

        Gson gson = new Gson();

        MemorialDayDetailFragment memorialDayDetailFragment = new MemorialDayDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MOMENTDAY", gson.toJson(clickedDetail));


        memorialDayDetailFragment.setArguments(bundle);


        // FragmentTransaction fragmentTransaction =
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();


        transaction.replace(R.id.frameDetailOfMDD, memorialDayDetailFragment);
        transaction.commit();

        //Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
        */
    }

    private void PopulateListView() {
        setListAdapter(new NoteListAdapter(getActivity(), momentNoteList));
    }
}
