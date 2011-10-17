package com.travenect.moodlogger.db;

import com.j256.ormlite.field.DatabaseField;

public class Mood extends BaseModel<Mood>
{
    @DatabaseField(index = true)
    private String name;

    @DatabaseField
    private boolean active = true;

    @DatabaseField
    private int position;

    @DatabaseField(canBeNull = true, foreign = true)
    private MoodCategory category;

    public Mood()
    {

    }
    
    public Mood(String name, boolean active, int position, MoodCategory category)
    {
        this.name = name;
        this.active = active;
        this.position = position;
        this.category = category;
    }

    @Override
    public String toString()
    {
        return this.name;
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

    public MoodCategory getCategory()
    {
        return category;
    }

    public void setCategory(MoodCategory category)
    {
        this.category = category;
    }

    public String getResourceName(String moodSet, int width)
    {
        if (moodSet.equalsIgnoreCase("seagal"))
        {
            return "seagal_" + width + "_seagal";
        }
        String moodName = this.name.replace(' ','_');
        return (moodSet + "_" + width + "_" + moodName).toLowerCase();
    }
}
