package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDifference {
	private double TotalTime;
	
    public double TotalTime() {
        return TotalTime;
    }
	
	public double getTimeDifference(String In, String Out) throws Throwable
    { 
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HH:mm:ss");   
        Date date1 = simpleDateFormat.parse(In); Date date2 = simpleDateFormat.parse(Out); 
        long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());
        long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
        long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;
        long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;
        TotalTime = (TotalTime + (differenceInHours + differenceInMinutes/60 + differenceInSeconds/3600)) - 1; 
        return TotalTime;
    }

    
}
