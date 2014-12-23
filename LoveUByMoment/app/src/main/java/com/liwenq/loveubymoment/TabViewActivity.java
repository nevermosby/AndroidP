package com.liwenq.loveubymoment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.liwenq.loveubymoment.fragment.TestFragment.FragmentFive;
import com.liwenq.loveubymoment.fragment.TestFragment.FragmentFour;
import com.liwenq.loveubymoment.fragment.TestFragment.FragmentOne;
import com.liwenq.loveubymoment.fragment.TestFragment.FragmentThree;
import com.liwenq.loveubymoment.fragment.TestFragment.FragmentTwo;


public class TabViewActivity extends Activity implements OnCheckedChangeListener {

    private RadioGroup rg;
    private RadioButton rb;

    private FragmentOne fone;
    private FragmentTwo ftwo;
    private FragmentThree fthree;
    private FragmentFour ffour;
    private FragmentFive ffive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);

        setupView();
        addListener();
    }

    private void setupView() {
        rg = (RadioGroup) findViewById(R.id.rg_select_main);
        rb = (RadioButton) findViewById(R.id.rb1);

        // 实例化fragment
        fone = new FragmentOne();
        ftwo = new FragmentTwo();
        fthree = new FragmentThree();
        ffour = new FragmentFour();
        ffive = new FragmentFive();

        // 初始化activity显示的fragment
        FragmentTransaction ftx = getFragmentManager().beginTransaction();
        ftx.add(R.id.main_frame, fone);
        ftx.commit();

        rb.setChecked(true); // 初始化选中当前推荐
    }

    private void addListener() {
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction ftx = getFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.rb1:
                ftx.replace(R.id.main_frame, fone);
                break;
            case R.id.rb2:
                ftx.replace(R.id.main_frame, ftwo);
                break;
            case R.id.rb3:
                ftx.replace(R.id.main_frame, fthree);
                break;
            case R.id.rb4:
                ftx.replace(R.id.main_frame, ffour);
                break;
            case R.id.rb5:
                ftx.replace(R.id.main_frame, ffive);
                break;
            default:
                break;
        }
        ftx.commit();
    }

}