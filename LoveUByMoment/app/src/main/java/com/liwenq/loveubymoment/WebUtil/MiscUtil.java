package com.liwenq.loveubymoment.WebUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liwenq on 9/1/2014.
 */
public class MiscUtil {

    public static Date Convert2Date(String dateStr){
        Date target =null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        try {
            target = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  target;
    }
}
