import java.util.*;
import java.io.*;
public class Clock{
	//Instance Vatiables
	private int hours;
	private int minutes;
	private int seconds;
	public Clock(int hours,int minutes, int seconds){
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public Clock(){
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	
	public void reset(int hours, int minutes, int seconds){
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public void reset(){
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	
	public void advance(){
		seconds++;
		if (seconds == 60){
			seconds = 0;
			minutes++;
		}
		if(minutes == 60){
			minutes = 0;
			hours++;
		}
		if (hours == 24){
			hours = 0;
		}
	}
	public String toString(){
		String time = "";
		if (hours < 10){
			time = "0";
		}
		time = time + Integer.toString(hours) + ":";
		if (minutes < 10){
			time = time + "0";
		}
		time = time + Integer.toString(minutes) + ":";
		if (seconds <10){
			time = time + 0;
		}
		time = time + Integer.toString(seconds);
		return time;
	}
}
