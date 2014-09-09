package com.liwenq.loveubymoment.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenq on 9/7/2014.
 */
public class NoteListAdapter extends BaseAdapter {

    List<MomentNote> momentNoteList;
    Context context;
    public NoteListAdapter(Context context, List<MomentNote> noteList){
        /* super(context, R.layout.item_view, momentNoteList); */
        this.context = context;
        this.momentNoteList = noteList;
    }

    @Override
    public int getCount() {
        return this.momentNoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.momentNoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.momentNoteList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // make sure we have a view
        View itemView = convertView;
        if(itemView==null){
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            itemView = mInflater.inflate(R.layout.item_view, parent, false);
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
    }
}
