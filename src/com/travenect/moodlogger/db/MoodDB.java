package com.travenect.moodlogger.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import android.content.Context;

public class MoodDB
{
    private static MoodDB instance = null;

    private DatabaseHelper helper = null;

    private Map<Object, Object> daoMap = new HashMap<Object, Object>();

    private MoodUser currentUser = null;

    public static MoodDB getInstance()
    {
        if (instance == null)
        {
            instance = new MoodDB();
        }
        return instance;
    }

    public MoodDB()
    {

    }

    /**
     * This should be called before everything else!
     * 
     * @param context
     */
    public void init(Context context)
    {
        this.close();
        helper = new DatabaseHelper(context);

        loadDefaults();
    }

    private void loadDefaults()
    {
        //get current user
        MoodPref userPref = get(MoodPref.class, "key", "CURRENT_USER");
        if (userPref != null)
        {
            MoodUser user = get(MoodUser.class, "username", userPref.getValue());
            if (user != null)
            {
                this.currentUser = user;
            }
            else
            //create new user with pref's username. this should never happen
            {
                setCurrentUser(initUser(userPref.getValue(), true));
            }
        }
        else
        // create an entirely new user
        {
            setCurrentUser(initUser("Offline user", true));
        }
    }

    private void setCurrentUser(MoodUser user)
    {
        this.currentUser = user;
        setPref("CURRENT_USER", user.getIdentifier());
    }

    public MoodUser getCurrentUser()
    {
        return this.currentUser;
    }

    /**
     * Create a new offline user with the given username
     * @param username
     * @throws SQLException 
     */
    private MoodUser initUser(String username, boolean isOffline)
    {
        MoodUser user = new MoodUser();
        user.setUsername(username);
        if (isOffline)
        {
            user.setIdentifier(""); //TODO some unique identifier with device id
        }
        else
        {
            user.setIdentifier(username);
        }
        user.setOffline(isOffline);

        user.save();

        return user;
    }

    public String getPref(String key)
    {
        MoodPref pref = get(MoodPref.class, "key", key);
        if (pref != null)
        {
            return pref.getValue();
        }
        return null;
    }

    public void setPref(String key, String value)
    {
        MoodPref pref = get(MoodPref.class, "key", key);
        if (pref == null)
        {
            pref = new MoodPref();
            pref.setKey(key);
        }

        pref.setValue(value);
        pref.save();
    }

    public void close()
    {
        daoMap.clear();
        if (helper != null)
        {
            helper.close();
            helper = null;
        }
    }

    private <T> T firstOrNull(List<T> list)
    {
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    protected <T> T get(Class<T> clazz, String field, Object value)
    {
        try
        {
            return firstOrNull(getDao(clazz).queryForEq(field, value));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> Dao<T, Integer> getDao(Class<T> daoClass)
    {
        if (daoMap.containsKey(daoClass))
        {
            return (Dao<T, Integer>) daoMap.get(daoClass);
        }
        try
        {
            Dao<T, Integer> dao = helper.getDao(daoClass);
            daoMap.put(daoClass, dao);
            return dao;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public List<MoodCategory> getCategories()
    {
        Dao<MoodCategory, Integer> dao = getDao(MoodCategory.class);
        QueryBuilder<MoodCategory, Integer> qb = dao.queryBuilder();
        qb.orderBy("position", true);
        try
        {
            return qb.query();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public List<Mood> getMoods(MoodCategory cat)
    {
        Dao<Mood, Integer> dao = getDao(Mood.class);
        QueryBuilder<Mood, Integer> qb = dao.queryBuilder();
        try
        {
            return qb.orderBy("position", true).where().eq("category_id", cat.getId()).query();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public String getCurrentMoodSet()
    {
        String value = getPref("CURRENT_MOODSET");
        if (value != null)
            return value;
        return "Default";
    }
    
    public void setCurrentMoodSet(String moodSet)
    {
        setPref("CURRENT_MOODSET", moodSet);
    }
    
    public MoodEntry addMoodEntry(Mood mood)
    {
        MoodEntry entry = new MoodEntry();
        entry.setMood(mood);
        entry.setDate(new Date());
        entry.setUser(currentUser);
        
        entry.setDao(this.getDao(MoodEntry.class));
        entry.save();
        
        return entry;
    }
}
