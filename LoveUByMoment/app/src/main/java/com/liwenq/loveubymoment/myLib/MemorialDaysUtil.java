package com.liwenq.loveubymoment.myLib;

import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.WebUtil.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liwenq on 9/6/2014.
 */
public class MemorialDaysUtil {

    private static String MET_DAY = "2011/09/25 19:00:00";
    private static String BECAME3_DAY = "2014/01/16 00:00:00";
    private static String HANBAO_BIRTHDAY = "2014/10/26 11:30:00";


    public static List<MomentNote> GetMemorialDayComing(){
        List<String> memorialDays = new ArrayList<String>();
        List<MomentNote> memorialDaysComing = new ArrayList<MomentNote>();
        memorialDays.add(MET_DAY+";The best lucky day in my life");
        memorialDays.add(BECAME3_DAY+";The day we became 3");
        memorialDays.add(HANBAO_BIRTHDAY+";The day hanbao was born");

        for(String input : memorialDays){
            String[] inputDateAndDesc = input.split(";");

            try{
                Date target = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(inputDateAndDesc[0]);
                int gapDays = DateUtil.GetDaysByNow(target);
                if(gapDays % 5 >=3){
                    String dayGapString = DateUtil.GetDayGapStringByNow(target);
                    memorialDaysComing.add(new MomentNote(dayGapString, inputDateAndDesc[1], "david"));
                }
            }catch (ParseException e) {
                e.printStackTrace();
            }


        }

        return memorialDaysComing;
    }

    public static List<MomentNote> GetMemorialDays() {
        Date metTime =null;
        Date became3Time = null;
        Date hanbaoBirth = null;
        List<MomentNote> momentNoteList = new ArrayList<MomentNote>();

        try {
            metTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(MET_DAY);
            became3Time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(BECAME3_DAY);
            hanbaoBirth = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(HANBAO_BIRTHDAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String metDayTitle = DateUtil.GetDayGapStringByNow(metTime);
        String metDayBody = "The best lucky day in my life";

        String became3DayTitle = DateUtil.GetDayGapStringByNow(became3Time);
        String became3DayBody = "The day we became 3";

        String hanbaoBirthTitle = DateUtil.GetDayGapStringByNow(hanbaoBirth);
        String hanbaoBirthBody = "The day hanbao was born";

        momentNoteList.add(new MomentNote(metDayTitle,metDayBody,"david"));
        momentNoteList.add(new MomentNote(became3DayTitle, became3DayBody,"david"));
        momentNoteList.add(new MomentNote(hanbaoBirthTitle,hanbaoBirthBody,"david"));

        return  momentNoteList;
    }
}
