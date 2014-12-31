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
import com.liwenq.loveubymoment.myLib.UtilHelper;

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
        if(UtilHelper.IsInternetAvailable(this.context)){
            new FetchMomentNotesAndBuildListView().execute();
        }else{
            Toast.makeText(this.context, "No internet access. Please check", Toast.LENGTH_LONG).show();
        }
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

            //Log.d(ViewPagerContentActivity.class.getName(), "current thread from onPostExecute: " + Thread.currentThread().getName());
            PopulateListView();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                String rawJson = HttpUtil.Get("https://hellonodemongo-davidlovezoe.rhcloud.com/MomentNote");
                if(rawJson!=null) {
                    //Log.d(ViewPagerContentActivity.class.getName(), "raw json from openshift: " + rawJson);
                    List<MomentNote> noteListFromOpenShift = JsonUtil.Serialize2Note(rawJson);
                    momentNoteList.addAll(noteListFromOpenShift);
                }
                else {
                    // Log.d(this.getClass().getName(), "raw json from openshift: " + rawJson);
                    Toast.makeText(context, "Cannot get Moment Note from internet and please check your internet access.", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception ex)
            {
                Log.d(this.getClass().getName(), "Got exception to get MomentNote: " + ex.getMessage());
                Toast.makeText(context, "Cannot get Moment Note from internet and please check your internet access.", Toast.LENGTH_LONG).show();
            }

            return null;
        }
    }

    private void PopulateListView() {
        setListAdapter(new NoteListAdapter(getActivity(), momentNoteList));
    }
}
