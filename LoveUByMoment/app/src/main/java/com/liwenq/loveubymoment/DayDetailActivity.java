package com.liwenq.loveubymoment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liwenq.loveubymoment.Entity.MomentNote;


public class DayDetailActivity extends Activity {

    TextView title;
    TextView body;

    MomentNote day = new MomentNote();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            title = (TextView)findViewById(R.id.textViewTitle);
            body = (TextView)findViewById(R.id.textViewBody);

            String detail = detailIntent.getStringExtra("MOMENTDAY");
            if(detail!=null){
                try{
                    day = new Gson().fromJson(detail, MomentNote.class);

                }catch (Exception ex){
                    title.setText("No detail found");
                    body.setText("");
                }

                title.setText(day.GetTitle());
                body.setText(day.GetBody());
            }
            else{
                title.setText("No detail found");
                body.setText("");
            }

        }else{

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
