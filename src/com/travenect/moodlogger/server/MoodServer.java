package com.travenect.moodlogger.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.travenect.moodlogger.db.MoodUser;

public class MoodServer
{
    private static MoodServer instance;
    
    private String serverAddress = "http://192.168.43.156";
    
    private MoodUser currentUser = null;
    
    public static MoodServer getInstance()
    {
        if (instance == null) 
        {
            instance = new MoodServer();
        }
        return instance;
    }
    
    public MoodUser getCurrentUser()
    {
        return currentUser;
    }
    
    public MoodUser login(String username, String password) throws ServerException
    {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(serverAddress + "/json/info");
        MoodUser user = null;

        try
        {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpclient.execute(httppost);

            String jsonResult = org.apache.http.util.EntityUtils.toString(response.getEntity());
            System.out.println("Result: "+jsonResult);
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();

            try
            {
                user = gson.fromJson(jsonResult, MoodUser.class);
            }
            catch (JsonSyntaxException e)
            {
                log(e);
            }
        }
        catch (ClientProtocolException e)
        {
            log(e);
        }
        catch (IOException e)
        {
            log(e);
        }

        return user;
    }
    
    private void log(Throwable t)
    {
        Log.e(getClass().toString(), t.getMessage(), t);
    }
}