package com.liwenq.loveubymoment.Entity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liwenq on 9/1/2014.
 */
public class MomentNote implements Cloneable {

    private String _id;
    private String _title;
    private String _body;
    private String _author;
    private String[] _comments;
    private Date _createAt;

    public MomentNote() {

    }

    public MomentNote(String title, String body, String author) {
        this._title = title;
        this._body = body;
        this._author = author;
        this._createAt = new Date();
    }

    public MomentNote(String id, String title, String body, String author, Date createAt) {
        this._title = title;
        this._body = body;
        this._author = author;
        this._id = id;
        this._createAt = createAt;
    }

    public String GetTitle(){
        return this._title;
    }

    public String GetBody(){
        return this._body;
    }

    public String GetAuthor(){
        return  this._author;
    }

    public Date GetCreateAt(){
        return  this._createAt;
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String showDate = formatter.format(GetCreateAt());
        return GetBody() + " from " + GetAuthor() + " at " + showDate;
    }
}
