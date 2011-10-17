package com.travenect.moodlogger.gui;

import com.travenect.moodlogger.R;
import com.travenect.moodlogger.db.Mood;
import com.travenect.moodlogger.main.MoodActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MoodStatusView extends LinearLayout
{
    private View view;
    
    private TextView statusText;
    private TextView statusDateText;
    private TextView statusTimeText;
    private ImageView statusImage;
    
    public MoodStatusView(Context context)
    {
        super(context);
        initLayout(context);
    }
    
    public MoodStatusView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initLayout(context);
    }
    
    public MoodStatusView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initLayout(context);
    }
    
    private void initLayout(Context context)
    {
        Log.i("CC", "MSV doing layout");
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = li.inflate(R.layout.moodstatus, this);

        this.statusText = (TextView)view.findViewById(R.id.moodStatusText);
        this.statusDateText = (TextView)view.findViewById(R.id.moodStatusDate);
        this.statusTimeText = (TextView)view.findViewById(R.id.moodStatusTime);
        this.statusImage = (ImageView)view.findViewById(R.id.moodStatusImage);
        
        LayoutParams lps = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(lps);
    }
}
