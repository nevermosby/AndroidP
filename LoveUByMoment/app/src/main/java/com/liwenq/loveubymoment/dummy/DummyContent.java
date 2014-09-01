package com.liwenq.loveubymoment.dummy;

import android.util.Log;

import com.liwenq.loveubymoment.Entity.MomentNote;
import com.liwenq.loveubymoment.WebUtil.HttpUtil;
import com.liwenq.loveubymoment.WebUtil.JsonUtil;
import com.liwenq.loveubymoment.myLib.TimeDiff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        Date metTime =null;
        Date now = new Date();
        Date hanbaoBirth =null;
        try {
            metTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2011/09/25 19:00:00");
            hanbaoBirth = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2014/01/16 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long[] diff = TimeDiff.getTimeDifference(metTime, now);
        String meTimeDuration = diff[0] + " days," + diff[1] + " hrs," + diff[2] + " mins," + diff[3] + " secs"; // and " + diff[4] + " millisecond(s)";
        //Log.d(TAG, "Time difference is " + diff[0] + " day(s)," + diff[1] + " hour(s)," + diff[2] + " minute(s)," + diff[3] + " second(s) and " + diff[4] + " millisecond(s)");
        diff = TimeDiff.getTimeDifference(hanbaoBirth, now);
        String hanbaoDuration = diff[0] + " days," + diff[1] + " hrs," + diff[2] + " mins," + diff[3] + " secs";

        // Add 3 sample items.
        addItem(new DummyItem("相遇纪念", meTimeDuration));
        addItem(new DummyItem("汉堡出现了", hanbaoDuration));
        // addItem(new DummyItem("3", "Item 3"));

        // fetch notes from openshift
        String rawJson = HttpUtil.Get("https://hellonodemongo-davidlovezoe.rhcloud.com/MomentNote");
        Log.d(DummyContent.class.getName(),"raw json from openshift: " + rawJson);
        List<MomentNote> momentNoteList = JsonUtil.Serialize2Note(rawJson);

        for(MomentNote note:momentNoteList){
            addItem(new DummyItem(note.GetTitle(), note.toString()));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
