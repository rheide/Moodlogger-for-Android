package com.travenect.moodlogger.db;

import android.graphics.Color;

import com.j256.ormlite.field.DatabaseField;

public class MoodCategory extends BaseModel<MoodCategory>
{
    @DatabaseField(index = true)
    private String name;

    @DatabaseField
    private String color;

    @DatabaseField
    private int position;

    public MoodCategory()
    {

    }

    public MoodCategory(String name, String color, int position)
    {
        this.name = name;
        this.color = color;
        this.position = position;
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

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getAndroidColor()
    {
        if (color.equals("green"))
            return Color.GREEN;
        else if (color.equals("red"))
            return Color.RED;
        else if (color.equals("blue"))
            return Color.BLUE;
        return Color.WHITE;
    }
}
