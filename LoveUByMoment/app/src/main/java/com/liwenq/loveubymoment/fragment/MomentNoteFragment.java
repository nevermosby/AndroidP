package com.liwenq.loveubymoment.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.MyAdapter.NoteListAdapter;
import com.liwenq.loveubymoment.R;
import com.liwenq.loveubymoment.WebUtil.HttpUtil;
import com.liwenq.loveubymoment.WebUtil.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenq on 9/6/2014.
 */
public class MomentNoteFragment extends ListFragment {
    private List<MomentNote> momentNoteList = new ArrayList<MomentNote>();
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_listview_fragment, null, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = getActivity().getApplicationContext();
        new FetchMomentNotesAndBuildListView().execute();
    }

    private class FetchMomentNotesAndBuildListView extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context,
                    "Fetching data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(context,
                    "Done", Toast.LENGTH_LONG).show();

            //Log.d(listview.class.getName(), "current thread from onPostExecute: " + Thread.currentThread().getName());
            PopulateListView();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String rawJson = HttpUtil.Get("https://hellonodemongo-davidlovezoe.rhcloud.com/MomentNote");
            //Log.d(listview.class.getName(), "raw json from openshift: " + rawJson);
            List<MomentNote> noteListFromOpenShift = JsonUtil.Serialize2Note(rawJson);
            momentNoteList.addAll(noteListFromOpenShift);
            return null;
        }
    }

    private void PopulateListView() {
        setListAdapter(new NoteListAdapter(getActivity(), momentNoteList));
    }
}
