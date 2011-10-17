package com.travenect.moodlogger.gui;

import java.util.HashMap;
import java.util.Map;

import com.travenect.moodlogger.R;
import com.travenect.moodlogger.db.Mood;
import com.travenect.moodlogger.db.MoodSet;
import com.travenect.moodlogger.main.MoodActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoodSelectorView extends LinearLayout
{
    private static final int SWIPE_MIN_DISTANCE = 60;
    private static final int SWIPE_THRESHOLD_VELOCITY = 150;
    private static Map<String, Integer> moodIdCache = new HashMap<String, Integer>();

    private final GestureDetector gdt = new GestureDetector(new GestureListener());

    private View view;

    private Mood mood;

    private MoodActivity ma;
    
    public MoodSelectorView(MoodActivity ma, String moodSet, Mood mood)
    {
        super(ma.getApplicationContext());
        this.ma = ma;
        this.mood = mood;
        LayoutInflater li = (LayoutInflater) ma.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = li.inflate(R.layout.moodselector, this);

        LayoutParams lps = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(lps);

        TextView tv = (TextView) this.view.findViewById(R.id.moodText);
        tv.setText(mood.getName());

        String moodName = mood.getResourceName(moodSet, 300);
        int id =0;
        if (moodIdCache.containsKey(moodName))
        {
            id = moodIdCache.get(moodName);
        }
        else
        {
            id = getResources().getIdentifier(moodName, "drawable", ma.getApplicationContext().getPackageName());
            moodIdCache.put(moodName, id);
        }
        
        ImageView imgView = (ImageView) this.view.findViewById(R.id.moodImage);
        imgView.setImageResource(id);
        
        imgView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (!gdt.onTouchEvent(event))
                {
                    if (event.getAction() == MotionEvent.ACTION_UP)
                    {
                        //process the click
                        MoodSelectorView.this.ma.moodSelected(MoodSelectorView.this.mood);
                    }
                }
                return true;
            }
        });
    }

    private class GestureListener extends SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)
            {
                Log.e("CC", "Bottom to top");
                ma.selectNextMoodCategory();
                return true;
            }
            else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)
            {
                Log.e("CC", "Top to bottom");
                ma.selectPreviousMoodCategory();
                return true;
            }
            return false;
        }
    }
}
