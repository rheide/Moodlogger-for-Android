package com.travenect.moodlogger.db;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class MoodEntry extends BaseModel<MoodEntry>
{
    @DatabaseField(foreign = true)
    private Mood mood;

    @DatabaseField(foreign = true)
    private MoodUser user;
    
    @DatabaseField
    private boolean uploaded = false;

    @DatabaseField
    private boolean deleted = false;
    
    @DatabaseField
    private Date date;
    
    @DatabaseField
    private double latitude = 0;
    
    @DatabaseField
    private double longtitude = 0;
    
    public MoodEntry()
    {

    }

    public Mood getMood()
    {
        return mood;
    }

    public void setMood(Mood mood)
    {
        this.mood = mood;
    }

    public MoodUser getUser()
    {
        return user;
    }

    public void setUser(MoodUser user)
    {
        this.user = user;
    }

    public boolean isUploaded()
    {
        return uploaded;
    }

    public void setUploaded(boolean uploaded)
    {
        this.uploaded = uploaded;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public double getLatitude()
    {
        return latitude;
    }
    
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
    
    public double getLongtitude()
    {
        return longtitude;
    }
    
    public void setLongtitude(double longtitude)
    {
        this.longtitude = longtitude;
    }

}
