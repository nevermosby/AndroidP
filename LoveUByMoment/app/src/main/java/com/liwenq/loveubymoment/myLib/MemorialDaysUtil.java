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

    public  static List<MomentNote> GetMemorialDays() {
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

        String metDayTitle = DateUtil.DayGapByNow(metTime);
        String metDayBody = "The best lucky day in my life";

        String became3DayTitle = DateUtil.DayGapByNow(became3Time);
        String became3DayBody = "The day we became 3";

        String hanbaoBirthTitle = DateUtil.DayGapByNow(hanbaoBirth);
        String hanbaoBirthBody = "The day hanbao was born";

        momentNoteList.add(new MomentNote(metDayTitle,metDayBody,"david"));
        momentNoteList.add(new MomentNote(became3DayTitle, became3DayBody,"david"));
        momentNoteList.add(new MomentNote(hanbaoBirthTitle,hanbaoBirthBody,"david"));

        return  momentNoteList;
    }
}
