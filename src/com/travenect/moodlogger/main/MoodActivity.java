package com.travenect.moodlogger.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.travenect.moodlogger.R;
import com.travenect.moodlogger.db.Mood;
import com.travenect.moodlogger.db.MoodCategory;
import com.travenect.moodlogger.db.MoodDB;
import com.travenect.moodlogger.db.MoodEntry;
import com.travenect.moodlogger.gui.MoodSelectorView;

public class MoodActivity extends Activity
{
    private List<MoodCategory> cats = new ArrayList<MoodCategory>();
    private MoodCategory selectedCategory;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood);

        initMoods();
    }

    private void initMoods()
    {
        MoodDB db = MoodDB.getInstance();
        
        this.cats = db.getCategories();
        this.selectedCategory = cats.get(1);

        reloadMoods();
    }

    private void reloadMoods()
    {
        //clear old moods:
        LinearLayout moodLayout = (LinearLayout) findViewById(R.id.MoodScrollLayout);
        moodLayout.removeAllViews();

        //update category label
        TextView catText = (TextView) findViewById(R.id.currentCategoryText);
        catText.setText(selectedCategory.getName());
        catText.setTextColor(selectedCategory.getAndroidColor());
        
        MoodDB db = MoodDB.getInstance();
        List<Mood> moods = db.getMoods(this.selectedCategory);

        String moodSet = db.getCurrentMoodSet();

        for (Mood mood: moods)
        {
            MoodSelectorView sel = new MoodSelectorView(this, moodSet, mood);
            moodLayout.addView(sel);
        }
    }
    
    public void moodSelected(Mood mood)
    {
        Log.e("CC", "Mood Selected: "+mood);
        MoodEntry entry = MoodDB.getInstance().addMoodEntry(mood);
        updateStatus(entry);
    }
    
    private void updateStatus(MoodEntry entry)
    {
        
    }
    
    public boolean selectNextMoodCategory()
    {
        int ix = cats.indexOf(selectedCategory);
        if (ix + 1 >= cats.size())
        {
            return false;
        }
        
        this.selectedCategory = cats.get(ix+1);
        
        //should probably do this asynchronous
        reloadMoods();
        
        return true;
    }
    
    public boolean selectPreviousMoodCategory()
    {
        int ix = cats.indexOf(selectedCategory);
        if (ix - 1 < 0)
        {
            return false;
        }
        
        this.selectedCategory = cats.get(ix-1);
        
        //should probably do this asynchronous
        reloadMoods();
        
        return true;
    }
    
}
