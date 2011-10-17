package com.travenect.moodlogger.db;

import com.j256.ormlite.field.DatabaseField;

public class MoodPref extends BaseModel<MoodPref>
{
    @DatabaseField(index = true)
    private String key;

    @DatabaseField
    private String value;

    public MoodPref()
    {
        super();
    }
    
    public MoodPref(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
