package com.travenect.moodlogger.main;

import android.app.Activity;
import android.os.Bundle;

import com.travenect.moodlogger.R;

public class AccountActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        //
        /*Button viewProfileButton = (Button) findViewById(R.id.viewProfile);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
              onClickViewProfile(view);
          }
        });*/
    }

    /*public void onClickViewProfile(View view) {
    
        Moodlogger appState = ((Moodlogger)getApplicationContext());
        String url = getResources().getString(R.string.BASE_URL) + "/u/" + appState.getUsername();
         
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse(url));
    
        startActivity( browse );
    }*/
}