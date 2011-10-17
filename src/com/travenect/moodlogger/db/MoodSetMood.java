package com.travenect.moodlogger.db;

import com.j256.ormlite.field.DatabaseField;

public class MoodSetMood extends BaseModel<MoodSetMood>
{
    @DatabaseField(foreign = true)
    private MoodSet moodSet;
    
    @DatabaseField(foreign = true)
    private Mood mood;

    public MoodSetMood()
    {

    }

    public MoodSetMood(MoodSet moodSet, Mood mood)
    {
        this.moodSet = moodSet;
        this.mood = mood;
    }
    
    public MoodSet getMoodSet()
    {
        return moodSet;
    }

    public void setMoodSet(MoodSet moodSet)
    {
        this.moodSet = moodSet;
    }

    public Mood getMood()
    {
        return mood;
    }

    public void setMood(Mood mood)
    {
        this.mood = mood;
    }

}
