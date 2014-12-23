package com.liwenq.loveubymoment.WebUtil;

import com.liwenq.loveubymoment.myLib.TimeDiff;

import java.util.Date;

/**
 * Created by liwenq on 9/4/2014.
 */
public class DateUtil {
    public static String GetDayGapStringByNow(Date input){
        Date now = new Date();
        long[] diff = TimeDiff.getTimeDifference(input, now);
        String returnDateString = diff[0] + " days, " + diff[1] + " hrs, " + diff[2] + " mins, " + diff[3] + " secs"; // and " + diff[4] + " millisecond(s)";
        return returnDateString;
    }

    public static int GetDaysByNow(Date input){
        Date now = new Date();
        long[] diff = TimeDiff.getTimeDifference(input, now);
        return (int) diff[0];
    }
}
