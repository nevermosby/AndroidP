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
    public  static List<MomentNote> AddMemorialDays() {
        Date metTime =null;
        Date hanbaoBirth=null;
        List<MomentNote> momentNoteList = new ArrayList<MomentNote>();

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

        return  momentNoteList;
    }
}
