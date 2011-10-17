package com.travenect.moodlogger.main;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.travenect.moodlogger.R;
import com.travenect.moodlogger.db.MoodDB;
import com.travenect.moodlogger.server.MoodServer;
import com.travenect.moodlogger.server.ServerException;

public class MoodLoggerActivity extends TabActivity
{
    private static boolean online = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initOnlineNess();
        initDatabase();
        initTabs();
    }
    
    private void initDatabase()
    {
        MoodDB.getInstance().init(getApplicationContext());
    }

    private void initOnlineNess()
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        online = haveConnectedWifi || haveConnectedMobile;
    }

    private void initTabs()
    {
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost(); // The activity TabHost
        TabHost.TabSpec spec; // Resusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        intent = new Intent().setClass(this, MoodActivity.class);
        spec = tabHost.newTabSpec("mood").setIndicator("Mood", res.getDrawable(R.drawable.tab_mood)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, HistoryActivity.class);
        spec = tabHost.newTabSpec("history").setIndicator("History", res.getDrawable(R.drawable.tab_history)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, StatsActivity.class);
        spec = tabHost.newTabSpec("stats").setIndicator("Stats", res.getDrawable(R.drawable.tab_stats)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, AccountActivity.class);
        spec = tabHost.newTabSpec("account").setIndicator("Account", res.getDrawable(R.drawable.tab_account)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }

    public final static boolean isOnline()
    {
        return online;
    }
}