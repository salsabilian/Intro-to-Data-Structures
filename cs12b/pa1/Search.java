//Shayan Salsabilian
//ssalsabi
//cs 12B
//1/16/2019
//This program uses binary search and sorting to find a target word in a specific file
//Search.java
import java.io.*;
import java.util.Scanner;
public class Search
{				//the public class that we will use for the program
	public static void mergeSort (String[]A, int[]lineNumber, int p, int r)
	{				//splits the middle of
		//the array for accurate sorting and then recursively sorts the array
		int q;			//will eventually hold the middle of the array for Binary Search
		if (p < r)
		{				//checks to make sure we haven't left the values of the array
			q = (p + r) / 2;	//takes the middle of the array
			mergeSort (A, lineNumber, p, q);	//break down the array into smaller chunks
			mergeSort (A, lineNumber, q + 1, r);	//once the arrays are all of length 1
			merge (A, lineNumber, p, q, r);	//merge the smaller chunks while snt i=0; i<A.length; i++){
		}
		//correctly
		}

		public static void merge (String[]A, int[]lineNumber, int p, int q, int r)
		{				//puts the arrays of length 1 back together in correct order
			int n1 = q - p + 1;		//size of the array from the middle going down
			int n2 = r - q;		//size of array from the middle going up
			String[]L = new String[n1];	//makes an array of the size needed for the left
			String[]R = new String[n2];	//makes an array of the size needed for the right
			int templineNumberL[]=new int[n1];//adjusts the line numbers array with the sorting array
			int templineNumberR[]=new int[n2];
			int i, j, k;		//used for loops to fill the array to the size needed
			for (i = 0; i < n1; i++)
			{				//fills the left array to the size needed of the original array
				L[i] = A[p + i];	//adjusting for indeces
				templineNumberL[i]=lineNumber[p+i];//transfer the first part of the array of the line count and transfer it
			}
			for (j = 0; j < n2; j++)
			{				//fills the right array to the size needed of the original
				//array
				R[j] = A[q + j + 1];	//adjusting for the indeces
				templineNumberR[j]=lineNumber[q+j+1]; //transfer the second part of the array of the line count and transfer it
			}
			i = 0;			//reset the variable values for the first size check
			j = 0;			//reset the variable values for second size checks
			for (k = p; k <= r; k++)
			{				//fill the new array based on what side the target word is on
				if (i < n1 && j < n2)
				{			//makes sure where still in the array
					if ((L[i].compareTo (R[j])) < 0)
					{			//compares the original arrays left and right side, if the right side is larger
						//compare to will produce a number less than 1          
						A[k] = L[i];	//starts the array from the beginning of the left array 
						lineNumber[k]=templineNumberL[i]; //transferring the line numbers back to the original array
						i++;		//add 1 to the original loop                        
					}
					else
					{			//start the array on the right
						A[k] = R[j];	//starts the array from the beginning of the right array
						lineNumber[k]=templineNumberR[j]; //transferring the line numbers back to the original array
						j++;		//increment the right
					}
				}
				else if (i < n1)
				{			//fills in the rest of the array until we reach the end of the array 
					A[k] = L[i];	//transfers the entire reset of the left array when we reach the end of n1
					lineNumber[k]=templineNumberL[i];//transfers the line numbers to the right position
					i++;//increment i
				}
				else
				{
					A[k] = R[j];	//fills in the rest of the array until we reach the end of the array
					lineNumber[k]=templineNumberR[j]; //transfers the line numbers to the right position in comparision with the original array
					j++;//increment j
				}
			}
		}
		public static int binarySearch (String[]A, int p, int r, String t)
		{				//function that checks the array in the quickest way to find if the target word is there or not
			int q;			//the variable that will hold the middle of the array
			if (p > r)
			{				//if were outside the array, the words not there return -1
				return -1;
			}
			else
			{				//otherwise proceed to search the array
				q = (p + r) / 2;	//find the middle
				if ((t.compareTo (A[q])) == 0)
				{			//check if the middle is the target word
					return q;	//if it is return the array number
				}
				else if ((t.compareTo (A[q])) < 0)
				{			//if the target word is less than 0 in value then we go left side
					return binarySearch (A, p, q - 1, t);	//and recurse through
				}
				else
				{
					return binarySearch (A, q + 1, r, t);	//else the target word is on the right and we recurse through
				}
			}
		}
		public static void main (String[]args) throws IOException
		{
			if (args.length<= 1)
			{				//makes sure file is not empty and is searching for an actual word
				System.err.println("Usage: Search file target1 [target2 ..]");
				System.exit(1);
			}
			try{ //A try and catch statement to deal with FileNotFoundException
				Scanner in = new Scanner(new File(args[0])); //scans the entire file
				in.useDelimiter ("\\Z");	//This is the end of the file 
				String s = in.next ();	//keeps going to the next line
				in.close();		//closes the file at the end
				String[] lines = s.split ("\n");	//determines the splits at each new line
				int lineCount = lines.length;	//saves the line count
				int i; //used to create the line number array
				int lineNumber[]=new int[lineCount]; //make a new array the size of the linecount
				for(i=0; i<lineCount;i++){//loops to make a array with line numbers that are increasing linearly from 1 to n, with n being the # of lines stored in line count
					lineNumber[i]=i+1;//the i+1 ensures it starts at 1 not 0
				}
				i=0; //resets i for the next loop for binary search
				mergeSort(lines,lineNumber, 0, lines.length-1); //sort the file words and there line numbers
				for(i=1;i<args.length; i++){ //loops to deal with all the different possible words
					int location=binarySearch(lines, 0, lines.length-1, args[i]); //looks for the target word in the newly made array and saves the location
					if(location==-1){ //if the array does not contain the word, then location returns -1
						System.out.println(args[i]+" not found");
					}else{ //print out the line the argument is on and the word searched
						System.out.println(args[i]+" found on line "+ lineNumber[location]);
					}
				}
			}

			catch(FileNotFoundException e){	//if we get a file exception
				System.err.println(e.getMessage()); //print out the error message
				System.err.println("Usage: Search file target1 [target2 ...]");
				System.exit(1);	//and exit the system		
			}
		}
	}
