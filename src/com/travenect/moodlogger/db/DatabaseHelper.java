package com.travenect.moodlogger.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
    private static final String DATABASE_NAME = "MoodLogger.db";
    private static final int DATABASE_VERSION = 12;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource)
    {
        try
        {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, MoodUser.class);
            TableUtils.createTable(connectionSource, MoodCategory.class);
            TableUtils.createTable(connectionSource, MoodPref.class);
            TableUtils.createTable(connectionSource, MoodSet.class);
            TableUtils.createTable(connectionSource, Mood.class);
            TableUtils.createTable(connectionSource, MoodSetMood.class);
            TableUtils.createTable(connectionSource, MoodEntry.class);
            loadInitialData(connectionSource);
        }
        catch (SQLException e)
        {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, MoodUser.class, true);
            TableUtils.dropTable(connectionSource, MoodCategory.class, true);
            TableUtils.dropTable(connectionSource, MoodPref.class, true);
            TableUtils.dropTable(connectionSource, MoodSet.class, true);
            TableUtils.dropTable(connectionSource, Mood.class, true);
            TableUtils.dropTable(connectionSource, MoodSetMood.class, true);
            TableUtils.dropTable(connectionSource, MoodEntry.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e)
        {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }
    
    private void loadInitialData(ConnectionSource connectionSource) throws SQLException
    {
        //default categories
        Dao<MoodCategory, Integer> cd = getDao(MoodCategory.class);
        MoodCategory positive,neutral,negative;
        cd.create(positive = new MoodCategory("Positive","green",1));
        cd.create(neutral = new MoodCategory("Neutral","blue",2));
        cd.create(negative = new MoodCategory("Negative","red",3));
        
        //default moods
        int ix = 1;
        Dao<Mood, Integer> md = getDao(Mood.class);
        md.create(new Mood("Awesome",true,ix++,positive));
        md.create(new Mood("Excellent",true,ix++,positive));
        md.create(new Mood("Excited",true,ix++,positive));
        md.create(new Mood("Happy",true,ix++,positive));
        md.create(new Mood("Joyful",true,ix++,positive));
        md.create(new Mood("Love",true,ix++,positive));
        md.create(new Mood("Lust",true,ix++,positive));
        md.create(new Mood("Naughty",true,ix++,positive));
        md.create(new Mood("Playful",true,ix++,positive));
        md.create(new Mood("Satisfied",true,ix++,positive));
        ix = 1;
        md.create(new Mood("Bored",true,ix++,neutral));
        md.create(new Mood("Clueless",true,ix++,neutral));
        md.create(new Mood("Confused",true,ix++,neutral));
        md.create(new Mood("Empty",true,ix++,neutral));
        md.create(new Mood("Insane",true,ix++,neutral));
        md.create(new Mood("Interested",true,ix++,neutral));
        md.create(new Mood("Meh",true,ix++,neutral));
        md.create(new Mood("Neutral",true,ix++,neutral));
        md.create(new Mood("Serene",true,ix++,neutral));
        md.create(new Mood("Sleepy",true,ix++,neutral));
        md.create(new Mood("Surprised",true,ix++,neutral));
        md.create(new Mood("Tired",true,ix++,neutral));
        ix=1;
        md.create(new Mood("Angry",true,ix++,negative));
        md.create(new Mood("Annoyed",true,ix++,negative));
        md.create(new Mood("Ashamed",true,ix++,negative));
        md.create(new Mood("Embarrassed",true,ix++,negative));
        md.create(new Mood("Frustrated",true,ix++,negative));
        md.create(new Mood("Hopeless",true,ix++,negative));
        md.create(new Mood("Lonely",true,ix++,negative));
        md.create(new Mood("Rage",true,ix++,negative));
        md.create(new Mood("Sad",true,ix++,negative));
        md.create(new Mood("Sick",true,ix++,negative));
        md.create(new Mood("Stressed",true,ix++,negative));
        md.create(new Mood("Uncomfortable",true,ix++,negative));
        md.create(new Mood("Worried",true,ix++,negative));
        
        //default moodsets
        Dao<MoodSet, Integer> ms = getDao(MoodSet.class);
        ms.create(new MoodSet("Default",true,1));
        ms.create(new MoodSet("Plain",true,2));
        ms.create(new MoodSet("Seagal",true,3));
    }
}
