--Queue
	--It is buit as a circular linked list
	--This is done so the front of the list can easily be obtained by gettinh the nextItem() of the last node.
--Job
	--The Job Class is a bunch of public variables.
	--It also contains a toString.
--TimeShare
	--main
		--in main it just does the general program flow It is basically the logic page on the program4.pdf
	--final calcs
		--this calculates the cpuUsage, averageWaitTime and the percentUsage.
		--cpuUsage
			--calculated with the total RunTime
		--idle time was found by counting every clockcycle where the input queue had an element but the Running queue did not.
		--percentUsage
			--calculated by dividing the cpuUsage by the idle time and the cpuUsage together and multiplying by 100.
			-- cpuUsage/(cpuUsage+idle)*100.
		--avgWaitTime 
			-- calculated by dividing the total wait time by the total number of jobs.
	--peek(Queue)
		--there is a peek method for each one of the times in the job class.
		--these methods will look at the first job in the specified queue and return the requested job time
	--set(Queue, int)
		--there is a set method for each of the times that do not get innitalized with the job
		--these methods will set the time of the specified queue to the inputed int and return an identical Queue to the inputed one. I don't think the return was necissary but I added the redundancy just in case
	--loadQueue(File)
		--loads the input Queue with the specified file.
	--hasStartTime(Queue)
		--This method effects the first position of the inputted queue
		--The job class innitalizes the startTime as -1. This method checks to see if that -1 has been changed to a posotive number.
	--isDone(Queue)
		--this method effects the first position of the inputted Queue
		--this method checks to see if the Job is done by adding the startTime to the runTime, then comparing that to the current clocktime.
	--  print(Queue)
		--this prints the final report. it will print the specified Queue in the job section of the report.
		--it will return invalid information if the finalCalcs() method is not called first.


--Example with 5 jobs
id	Atime	Stime	Rtime	Wtime	Ttime
job1	1	1	5	0	5
job2	4	6	2	2	4
job3	9	9	4	0	4
job4	12	13	6	1	7
job5	18	19	25	1	26

calcs are shown in the final calc section.
