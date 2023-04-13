//Shayan Salsabilian
//ssalsabi
//3/3/19
//12B
//creates a simulation of a number of jobs being processed by multiple processors
//Simulation.java
import java.io.*;
import java.util.Scanner;

public class Simulation
{
	static Job getJob (Scanner in) //scan from a file to make a new jobs 
	{
		String[]s = in.nextLine ().split (" ");//create a new job from each new line
		int a = Integer.parseInt (s[0]);
		int d = Integer.parseInt (s[1]);
		return new Job (a, d);//return the jobs
	}
	public static void main (String[]args) throws IOException
	{
		Scanner in = null; //start the scanners and printwriters as nulls
		PrintWriter trc = null;
		PrintWriter rpt = null;
		if (args.length != 1) // we need exactly an argument of length 1
		{
			System.out.println ("Usage: Simulation <input_file>");
		}
		//open files
		in = new Scanner (new File (args[0])); //scan in from the file in args[0]
		trc = new PrintWriter (new FileWriter (args[0] + ".trc"));//create a new file called args[0].trc
		rpt = new PrintWriter (new FileWriter (args[0] + ".rpt"));//create a new file called args[0].rpt
		String Line = in.nextLine ();//grab the first line that will tell us the number of jobs
		int m = Integer.parseInt (Line);//parse that integer
		int n = 1;//start n at 1
		int time = 0;//start time at 0
		int wait = 0;//will hold the current wait, this will be used to get the Maxwait, total wait and average wait
		int Maxwait = -1;//will eventually hold the max wait, gotten from comparing the wait to its previous values and saves the largest
		int index = -1;//will be used to find the lowest index to place the job 
		float totalwait = 0;//will contain the total wait by adding the current wait to the total wait each time
		Job Storage[] = new Job[m];//create a Job storage array to maintain the order and for printing purposes
		for (int i = 0; i < m; i++)//store all the jobs in the job storage array
		{
			Storage[i] = getJob(in);
		}
		rpt.println("Report file: "+args[0]+".rpt");//printing the starting information to report
		rpt.println(m+" Jobs:");//prints the number of jobs
		for(int i = 0; i < Storage.length; i++){//prints all the jobs out on one line
			rpt.print(Storage[i]+" ");
		}
		rpt.println();//print some new lines
		rpt.println();
		rpt.println("***********************************************************");//end of the printing of starting information for report
		trc.println("Trace file: "+args[0]+".trc");//printing the starting information to trace
		trc.println(m+" Jobs:");//prints the number of jobs
		for(int i = 0; i < Storage.length; i++){//prints all the jobs out on one line
			trc.print(Storage[i]+" ");
		}
		trc.println();//print some new lines
		trc.println();//end of printing starting information for trace 
		while (n < m)
		{
			trc.println("*****************************");//prints out the number of processors we have each time we go through the loop to trace
			if(n == 1){//this deals with the plural vs singular number of processors
				trc.println( n +" processor:");
			}else{
				trc.println( n +" processors:");
			}
			trc.println("*****************************");
			trc.println("time=" + time);//prints out the information we have at time 0
			trc.print("0: ");
			for(int i = 0; i < Storage.length; i++){//prints out the jobs on processor line 0 at time 0 since we havent stated processing jobs yet at time 0
				trc.print(Storage[i]+" ");
			}
			trc.println();//print out a new line after we print out the processor
			for(int i = 1;i <= n;i++){//prints out the number of processors we have each time we go through the loop
				trc.println( i + ": ");
			}
			trc.println();//then print out a new line
			Queue Q[] = new Queue[n + 1];//make an array of queues
			for(int i = 0; i <= n; i++){//initialize the array of queues
				Q[i] = new Queue();
			}
			for(int i = 0;i < m; i++){//enqueue all the jobs into position 0 of the queue array
				Q[0].enqueue(Storage[i]);
			}
			Job check4 = new Job(-1,-1);//starting a check with random values will later hold the peek which will determine whether we need to exit the loop or not
			//if Q is of length m meaning we have finished processing everything and can leave the loop
			
			while((Q[0].length() != m)||((check4.getFinish()) == -1))//enter the loop
			{
				if(Q[0].length() != 0){
					check4 = (Job) Q[0].peek();//peeking each time to see if the first item in Q[0] has a finish time if it does and the length of Q is m then were done
				}
				int multprintstop = 0;//will stop multiple prints
				int p = 0;//p gets all the finish time
				int lowest = -1;//will eventually hold the lowest finish time
				for(int i = 1; i <= n; i++){//loop through the entire array of queues
					if(Q[i].length() != 0){//makes sure we dont peek at a null Queue
						Job check2 = (Job) Q[i].peek();//peek at the queue
						p = check2.getFinish();//get the finish value
						if((lowest == -1)||(p < lowest)){//if the current lowest finish value is less than the one we have now or its our first time entering the loop
							lowest = p;//we set the lowest value to the current finish value
						}
					}
				}
				if(time == lowest){//if time reaches the lowest finish value
					for(int i = 1; i < Q.length; i++){//go through all the queues
						if(Q[i].length() != 0){//make sure we dont peek at a null
							Job check3 = (Job) Q[i].peek(); //peek at the first value in each processor
							if(check3.getFinish() == lowest){//if those also have the same finish time 
								Job fin = (Job) Q[i].dequeue();//dequeue those items as well
								Q[0].enqueue(fin);//and reenqueue them back into the position 0 of the queue array
								if(Q[i].length() != 0){//make sure we dont peek at a null queue
									Job peek = (Job) Q[i].peek();//peek at the next item in that queue
									peek.computeFinishTime(time);//compute the finish time based on the time the last one stopped
									wait = (peek.getFinish())-(peek.getArrival())-(peek.getDuration());//calculate the current wait time
									totalwait = totalwait+wait;//add it to the total wait time
									if((Maxwait == -1)||(wait>Maxwait)){//if its our first time calculating maxWait or wait is greater than maxWait
										Maxwait = wait;//make maxWait equal wait
									}
								}
							}
						}
					}
					multprintstop++;
				}
						int size = Q[0].length();//holds the number of jobs left therefore all the possible jobs that could have the same arrival time
						for(int s = 1; s <= size; s++){//loop through for the entire length of Q[0] to process all the jobs that have the same arrival time
							if(Q[0].length() != 0){//make sure we dont run into a null peeking at an empty queue
								Job check = (Job) Q[0].peek(); //peek again to see if after we handle the first arrival if we have more arrival times after that that match the 
								//original and handle those as well
								if(time == check.getArrival()){ //if we do have a time that matches arrival time
									Job original = (Job) Q[0].dequeue();//dequeue the job from position 0
									int lowest1 = -1;//will be used to try to find the lowest processor to place the job in
									for(int i = 1; i < Q.length; i++){//we will loop through the entire array looking for the smallest length queue
										int currentlength = (Q[i].length());//check the length of the current queue were on
										if((currentlength < lowest1) || (lowest1 == -1)){//if the current length is the lowest or its our first time going into the loop
											lowest1 = currentlength;//make lowest equal current length
											index = i;//and make the index be the position where the lowest length is
										}
									}
									if(Q[index].length() == 0){//if there is no items behind the current index, you can compute finish time as soon as the job arrives
										check.computeFinishTime(time);//compute finish time
										wait = (check.getFinish())-(check.getArrival())-(check.getDuration());//compute the wait time
										totalwait = totalwait+wait;//add the wait time to the total wait time
										if((Maxwait == -1) || (wait > Maxwait)){//if the current wait is the largest or its our first time entering the loop the Maxwait 
										//is the current wait
											Maxwait = wait;
										}
									}
									Q[index].enqueue(check);//enqueue the item at the index that is the shortest which we determined earlier
									multprintstop++;
								}
							}
						}
				if(multprintstop != 0){//will only print if an arrival or finish event occurs
					trc.println("time=" + time);
					for(int i = 0; i <= n; i++){
						trc.println( i +": " + Q[i]);
					}
					trc.println();
				}
				time++;//increment the time
			}
			float averagewait = (totalwait) / (Q[0].length());//calculate the average wait by dividing the total wait by the # of jobs
			if(n == 1){//print out the wait times to report
				rpt.printf("%d processor: totalWait=%.0f, maxWait=%d, averageWait=%.2f\n", n , totalwait, Maxwait, averagewait);
			}else{
				rpt.printf("%d processors: totalWait=%.0f, maxWait=%d, averageWait=%.2f\n", n, totalwait, Maxwait, averagewait);	
			}
			Maxwait = -1;//reset the waits
			totalwait = 0;
			wait = 0;
			for(int i = 0; i < Storage.length; i++){//reset the finish times for the jobs
				Job clean = Storage[i];
				clean.resetFinishTime();
			}
			n++;//increment the number of processors
			time = 0;//reset time

		}
		in.close ();//close all the files
		rpt.close ();
		trc.close ();
	}
}
