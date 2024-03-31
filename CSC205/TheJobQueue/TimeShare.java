import java.util.*;
import java.io.*;
import java.text.*;
public class TimeShare{
	private static int clock = 1;
	private static double idle = 0;
	private static double avgWaitTime = 0;
	private static double cpuUsage = 0;
	private static double cpuPerUse = 0;
	private static Queue Input = new Queue();
	public static void main(String args[]){
		File f = new File(args[0]);
		try{
			if(f.exists() && f.canRead()){
				loadQueue(f);
				Queue Running = new Queue();
				Queue Finished = new Queue(); 
				boolean done = true;
				while(done == true){
      					if(!Input.isEmpty()){
          					int ArrivalTime = peekAT(Input); 
               					if(ArrivalTime == clock){
							Job Hold = (Job) Input.dequeue();
                        				if(Running.isEmpty())
								Hold.startTime=clock;
							Running.enqueue(Hold);
						}
					}
					if(!Running.isEmpty()){
                                                if(!hasStartTime(Running)&&!Running.isEmpty()){
							Running = setST(Running, clock-1);
                                                        Running = setWT(Running,(peekST(Running) - peekAT(Running)));
                                                }

						if(isDone(Running) && hasStartTime(Running)){
							Running = setTT(Running,(peekRT(Running)+peekWT(Running)));
							Job Hold = (Job) Running.dequeue();
							Finished.enqueue(Hold);
						}
					}
					if(Running.isEmpty() && !Input.isEmpty())
                                                idle++;

					else if(Running.isEmpty() && Input.isEmpty())
						done = false;
					clock++;
				}
				finalcalcs(Finished);
				print(Finished);
			} 	
			else {
				System.out.println("File Not Found Please Try Again");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private static void finalcalcs(Queue calcedQueue) throws CloneNotSupportedException{
		Queue calcQueue =(Queue) calcedQueue.clone();
		double totalWait = 0;
		double numElements=0;
		while(!calcQueue.isEmpty()){
			Job checkTimes =(Job) calcQueue.dequeue();
			totalWait += checkTimes.waitTime;
			cpuUsage += checkTimes.runTime;
			numElements++;
		}
		cpuPerUse = (cpuUsage / (cpuUsage+idle))*100;
		avgWaitTime = totalWait/numElements;
		
	} 
	private static int peekST(Queue peek) throws CloneNotSupportedException{
		Queue copy = (Queue) peek.clone();
		Job Starget = (Job) copy.dequeue();
		//System.out.println("Starget =" + Starget);
		return Starget.startTime;
	}
	private static int peekAT(Queue peek) throws CloneNotSupportedException{
		Queue copy = (Queue) peek.clone();
		Job ATarget = (Job) copy.dequeue();
		//System.out.println("ATarget = "+ ATarget);
		return ATarget.arrivalTime;
	}
	private static int peekRT(Queue peek) throws CloneNotSupportedException{
		Queue copy = (Queue) peek.clone();
		Job RTarget = (Job) copy.dequeue();
		//System.out.println("RTarget =" + RTarget);
		return RTarget.runTime;
	}
	private static int peekWT(Queue peek) throws CloneNotSupportedException{
		Queue copy = (Queue) peek.clone();
		Job WTarget= (Job) copy.dequeue();
		//System.out.println("WTarget =" + WTarget);
		return WTarget.waitTime;
	}
	private static int peekTT(Queue peek) throws CloneNotSupportedException{
		Queue copy = (Queue) peek.clone();
		Job TTarget = (Job) copy.dequeue();
		//System.out.println("TTarget =" +TTarget);
		return TTarget.turnTime;
	}
	private static Queue setST(Queue set, int startTo) throws CloneNotSupportedException{
		Queue hold = new Queue();
		Job setST = (Job) set.dequeue();
		setST.startTime = startTo;
		hold.enqueue(setST);
		while(!set.isEmpty())
			hold.enqueue(set.dequeue());
		//System.out.println("ST set to" + startTo);
		return hold;
	}
	private static Queue setWT(Queue set, int waitTo) throws CloneNotSupportedException{
		Queue hold = new Queue();
		Job setWT = (Job) set.dequeue();
		setWT.waitTime = waitTo;
		hold.enqueue(setWT);
		while(!set.isEmpty())
			hold.enqueue(set.dequeue());
		//System.out.println("WT set to" + waitTo);
		return hold;
	}
	private static Queue setTT(Queue set, int turnTo) throws CloneNotSupportedException{
		Queue hold = new Queue();
		Job setTT = (Job) set.dequeue();
		setTT.turnTime = turnTo;
		hold.enqueue(setTT);
		while(!set.isEmpty())
			hold.enqueue(set.dequeue());
		//System.out.println("TT set to" + turnTo);
		return hold;
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
				Input.enqueue(holdJob);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	private static boolean hasStartTime(Queue chktime)throws CloneNotSupportedException{
		if(peekST(chktime) > 0){
			return true;
		}
		else{
			return false;
		}
	}
	private static boolean isDone(Queue chkDone)throws CloneNotSupportedException{
		int startTime = peekST(chkDone);
		int runTime = peekRT(chkDone);
		int finishedTime = startTime + runTime;
		if(finishedTime == clock)
			return true;
		else
			return false;	
	}
	private static void print(Queue printTgt)throws CloneNotSupportedException{
		Queue printClone = (Queue) printTgt.clone();
		System.out.println("Job Control Analysis: Summary Report\n");
		System.out.println("Job Id\tArrival\tStart\tRun \tWait\tTurnaround");
		System.out.println("      \ttime   \ttime \ttime\ttime\ttime      ");
		System.out.println("------\t-------\t-----\t----\t----\t----------");
		while(!printClone.isEmpty()){
			Job print = (Job) printClone.dequeue();
			System.out.println(print.toString());
		}
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("   Average Wait Time => "+ df.format(avgWaitTime));
		System.out.println("           CPU Usage => "+ df.format(cpuUsage));
		System.out.println("            CPU Idle => "+ df.format(idle));
		System.out.println("       CPU Usage (%) => "+ df.format(cpuPerUse)+ "%");
	}
}
