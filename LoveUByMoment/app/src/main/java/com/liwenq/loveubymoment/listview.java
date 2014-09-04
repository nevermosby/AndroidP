package com.liwenq.loveubymoment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.WebUtil.DateUtil;
import com.liwenq.loveubymoment.WebUtil.HttpUtil;
import com.liwenq.loveubymoment.WebUtil.JsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class listview extends Activity {

    private List<MomentNote> momentNoteList = new ArrayList<MomentNote>();
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        new FetchMomentNotesAndBuildListView().execute();

        //AddMemorialDays();
        //PopulateListView();
    }

    private class FetchMomentNotesAndBuildListView extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),
                    "Fetching data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),
                    "Done", Toast.LENGTH_LONG).show();

            Log.d(listview.class.getName(), "current thread from onPostExecute: " + Thread.currentThread().getName());
            // PopulateListView();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String rawJson = HttpUtil.Get("https://hellonodemongo-davidlovezoe.rhcloud.com/MomentNote");
                    Log.d(listview.class.getName(), "raw json from openshift: " + rawJson);
                    List<MomentNote> noteListFromOpenShift = JsonUtil.Serialize2Note(rawJson);
                    momentNoteList.addAll(noteListFromOpenShift);
                    AddMemorialDays();

                    mHandler.post(new Runnable() {
                        @Override
                        public void run () {
                            Log.d(listview.class.getName(), "current thread from handler post: " + Thread.currentThread().getName());
                            PopulateListView();
                        }
                    });
                }
            }).start();
            return null;
        }
    }


    private void PopulateListView() {
        ArrayAdapter<MomentNote> adapter = new NoteListAdapter();
        ListView list = (ListView) findViewById(R.id.noteListView);
        list.setAdapter(adapter);
    }

    private void AddMemorialDays() {
        Date metTime =null;
        Date hanbaoBirth=null;
        try {
            metTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2011/09/25 19:00:00");
            hanbaoBirth = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2014/01/16 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String metDayTitle = DateUtil.DayGapByNow(metTime);
        String metDayBody = "The best lucky day in my life";

        String hanbaoBirthTitle = DateUtil.DayGapByNow(hanbaoBirth);
        String hanbaoBirthBody = "The day we became 3";

        momentNoteList.add(new MomentNote(metDayTitle,metDayBody,"david"));
        momentNoteList.add(new MomentNote(hanbaoBirthTitle,hanbaoBirthBody,"david"));
    }

    private class NoteListAdapter extends ArrayAdapter<MomentNote> {
        public NoteListAdapter(){
            super(listview.this, R.layout.item_view, momentNoteList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // make sure we have a view
            View itemView = convertView;
            if(itemView==null){
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            // find the note to work with
            MomentNote currentNote = momentNoteList.get(position);

            // fill the view
            ImageView imgView = (ImageView)itemView.findViewById(R.id.item_icon);
            imgView.setImageResource(R.drawable.ic_launcher);

            TextView titleTxtView = (TextView)itemView.findViewById(R.id.itemview_title);
            titleTxtView.setText(currentNote.GetTitle());

            TextView bodyTxtView = (TextView)itemView.findViewById(R.id.itemview_body);
            bodyTxtView.setText(currentNote.GetBody());

            return itemView;
//            return super.getView(position, convertView, parent);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.listview, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
