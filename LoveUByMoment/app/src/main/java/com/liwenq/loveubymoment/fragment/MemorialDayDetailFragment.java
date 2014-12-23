package com.liwenq.loveubymoment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.gson.Gson;
import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.R;

/**
 * A fragment representing a single MemorialDay detail screen.
 * on handsets.
 */
public class MemorialDayDetailFragment extends Fragment {
    TextView txtTitle;
    TextView txtBody;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_memorial_day_detail, null, false);
        txtTitle = (TextView)view.findViewById(R.id.textViewTitleOfMDD);
        txtBody = (TextView)view.findViewById(R.id.textViewBodyOfMMD);

        Bundle bundle = getArguments();
        MomentNote day = new MomentNote();
        if(bundle != null){
            String detail = bundle.getString("MOMENTDAY", "no argument pass");
            try{
                day = new Gson().fromJson(detail, MomentNote.class);

            }catch (Exception ex){
                txtTitle.setText(detail);
                txtBody.setText("");
            }

            txtTitle.setText(day.GetTitle());
            txtBody.setText(day.GetBody());
        }else{

        }

        return view;
    }

    public void updateDetail(MomentNote clickedMomentDay) {
        txtTitle.setText(clickedMomentDay.GetTitle());
        txtBody.setText(clickedMomentDay.GetBody());
    }
}
