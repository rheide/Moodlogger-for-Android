package com.travenect.moodlogger.db;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;

public class BaseModel<T> extends BaseDaoEnabled<T, Integer>
{
    @DatabaseField(generatedId = true)
    int id;

    @SuppressWarnings("unchecked")
    public BaseModel()
    {
        Dao<T,Integer> dao = (Dao<T, Integer>) MoodDB.getInstance().getDao(this.getClass());
        if (dao != null)
        {
            super.setDao(dao);
        }
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public void save()
    {
        try
        {
            if (this.id > 0)
            {
                this.update();
            }
            else
            {
                this.id = this.create();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
