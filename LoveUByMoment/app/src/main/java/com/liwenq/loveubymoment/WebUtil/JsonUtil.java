package com.liwenq.loveubymoment.WebUtil;

import com.liwenq.loveubymoment.Entity.MomentNote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liwenq on 9/1/2014.
 */
public class JsonUtil {

    public static List<MomentNote> Serialize2Note(String rawJson) {

        JSONArray jsonArray = null;
        List<MomentNote> momentNoteList = new ArrayList<MomentNote>();
        try {
            jsonArray = new JSONArray(rawJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonArray != null) for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String dateStr = null;
            String title =null;
            String body=null;
            String id=null;
            String author=null;
            if (jsonObject != null) {
                try {
                    title = jsonObject.getString("title");
                    body = jsonObject.getString("body");
                    id = jsonObject.getString("_id");
                    author = jsonObject.getString("author");
                    // String[] comments
                    dateStr = jsonObject.getString("createAt");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // parse date string
            Date createAt = MiscUtil.Convert2Date(dateStr);
            momentNoteList.add(new MomentNote(id,title,body,author,createAt));
        }

        return momentNoteList;
    }
}
