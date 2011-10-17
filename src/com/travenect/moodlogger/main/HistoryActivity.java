package com.travenect.moodlogger.main;

import android.app.Activity;
import android.os.Bundle;

import com.travenect.moodlogger.R;

public class HistoryActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        /*Button viewProfileButton = (Button) findViewById(R.id.viewProfile);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
              onClickViewProfile(view);
          }
        });*/
    }
}
