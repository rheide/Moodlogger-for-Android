package com.travenect.moodlogger.server;

@SuppressWarnings("serial")
public class ServerException extends Exception
{
    public ServerException(String msg)
    {
        super(msg);
    }
    
    public ServerException(Throwable t)
    {
        super(t);
    }
}
