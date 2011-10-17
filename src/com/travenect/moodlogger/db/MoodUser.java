package com.travenect.moodlogger.db;

import com.j256.ormlite.field.DatabaseField;

public class MoodUser extends BaseModel<MoodUser>
{

    @DatabaseField(index = true)
    private String username; //local username (can be whatever)

    @DatabaseField(index = true)
    private String identifier; //server username or client device identifier

    @DatabaseField
    private String email;

    @DatabaseField
    private boolean isOffline = true;

    @DatabaseField
    private boolean loggingLocation = true;

    public MoodUser()
    {

    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public boolean isOffline()
    {
        return isOffline;
    }

    public void setOffline(boolean isOffline)
    {
        this.isOffline = isOffline;
    }

    public void setLoggingLocation(boolean loggingLocation)
    {
        this.loggingLocation = loggingLocation;
    }

    public boolean isLoggingLocation()
    {
        return loggingLocation;
    }

}
