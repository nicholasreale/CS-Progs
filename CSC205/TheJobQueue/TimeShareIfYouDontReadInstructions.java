import java.util.*;
import java.io.*;
import java.text.*;
public class TimeShare{
	private static Queue JobQueue = new Queue();
	private static double cpuUsage = 0;
	private static double cpuIdle = 0; 
	private static double cpuPerUse = 0;
	private static double avgWaitTime = 0;
	public static void main(String[] args){
		File f = new File(args[0]);
		if(f.exists() && f.canRead()){
			loadQueue(f);
			setStartTime();
			calcAvgTimes();
			print();
		}
		else {
			System.out.println("File Not Found Please Try Again");
		} 
	}
	private static void loadQueue(File file){
		try{
			String jobId;
			int aTime, rTime;
			Scanner fileIn = new Scanner(file);
			while(fileIn.hasNextLine()){
				jobId = fileIn.next();
				aTime = fileIn.nextInt();
				rTime = fileIn.nextInt();
				Job holdJob = new Job(jobId, aTime, rTime);
				JobQueue.enqueue(holdJob);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private static void setStartTime(){
		Queue placeHold = new Queue();
		int currTime = 0;
		Job checkJob = new Job();
		while(!JobQueue.isEmpty()){
			checkJob = (Job) JobQueue.dequeue();
			if(checkJob.getATime() > currTime){
				checkJob.setSTime(checkJob.getATime());
				if( currTime != 0)
					cpuIdle += checkJob.getATime() - currTime;
				currTime = checkJob.getATime() + checkJob.getRTime();
				placeHold.enqueue(checkJob);
			}
			else{
				checkJob.setSTime(currTime);
				currTime += checkJob.getRTime();
				placeHold.enqueue(checkJob);
			}		
		}
		JobQueue = placeHold;
	}
	private static void calcAvgTimes(){
		Queue placeHold = new Queue();
		Job checkJob = new Job();
		double totalWaitTime = 0;
		double count = 0;
		while(!JobQueue.isEmpty()){
			checkJob = (Job) JobQueue.dequeue();
			totalWaitTime += checkJob.getWTime();
			count++;
			cpuUsage += checkJob.getRTime();
			placeHold.enqueue(checkJob);
		}
		cpuPerUse = (cpuUsage/(cpuUsage+cpuIdle))*100;
		avgWaitTime = totalWaitTime/count;
		JobQueue = placeHold;
	}
	private static void print(){
		Queue placeHold = new Queue();
		Job print = new Job();
		System.out.println("Job Control Analysis: Summary Report\n");
		System.out.println("Job Id\tArrival\tStart\tRun \tWait\tTurnaround");
		System.out.println("      \ttime   \ttime \ttime\ttime\ttime      ");
		System.out.println("------\t-------\t-----\t----\t----\t----------");
		while(!JobQueue.isEmpty()){
			print = (Job) JobQueue.dequeue();
			System.out.println(print.toString());
			placeHold.enqueue(print);
		}
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("   Average Wait Time => "+ df.format(avgWaitTime));
		System.out.println("           CPU Usage => "+ df.format(cpuUsage));
		System.out.println("            CPU Idle => "+ df.format(cpuIdle));
		System.out.println("       CPU Usage (%) => "+ df.format(cpuPerUse)+ "%");
	}
}
