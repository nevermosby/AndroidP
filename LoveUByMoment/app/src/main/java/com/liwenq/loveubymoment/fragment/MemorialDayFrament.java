package com.liwenq.loveubymoment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.MyAdapter.NoteListAdapter;
import com.liwenq.loveubymoment.R;
import com.liwenq.loveubymoment.myLib.MemorialDaysUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenq on 9/6/2014.
 */
public class MemorialDayFrament extends ListFragment {
    private List<MomentNote> momentNoteList = new ArrayList<MomentNote>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_listview_fragment, null,false);

//        // populate data
//        momentNoteList.addAll(MemorialDaysUtil.AddMemorialDays());
//
//        // initialize list view
//        PopulateListView();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // populate data
        momentNoteList.addAll(MemorialDaysUtil.AddMemorialDays());

        // initialize list view
        PopulateListView();
    }

    private void PopulateListView() {
        setListAdapter(new NoteListAdapter(getActivity(), momentNoteList));
    }
}
