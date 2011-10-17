package com.travenect.moodlogger.db;

import com.j256.ormlite.field.DatabaseField;

public class MoodSet extends BaseModel<MoodSet>
{
    @DatabaseField(index = true)
    private String name;

    @DatabaseField
    private boolean active = true;

    @DatabaseField
    private int position;

    public MoodSet()
    {

    }
    
    public MoodSet(String name, boolean active, int position)
    {
        this.name = name;
        this.active = active;
        this.position = position;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

}
