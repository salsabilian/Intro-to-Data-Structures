//Shayan Salsabilian
//Queens.java
//12B
//1/28/19
//ssalsabi
//prints out all the possible solutions for the placement of queens on a n by n chess board
import java.io.*;
import java.util.Scanner;

class Queens
{
  static void placeQueen (int[][]B, int i, int j)
  {
    int q = i + 1;		//use temp vars
    while (q < B.length)
      {				//handles the bottom vertical
	B[q][j]--;
	q++;
      }
    int n = i;			//use temp vars
    int m = j;
    while ((n < B.length) && (m > 0))
      {				//handles the bottom left diagonal
	B[n][m]--;
	n++;
	m--;
      }
    n = i;			//reset temp vars
    m = j;
    while ((n < B.length) && (m < B.length))
      {				//handles the bottom right diagonal
	B[n][m]--;
	n++;
	m++;
      }
    B[i][j] = 1;		//place queen
    B[i][0] = j;		//place column used on row 0
  }

  static void removeQueen (int[][]B, int i, int j)
  {
    int q = i + 1;
    while (q < B.length)
      {				//handles the bottom vertical
	B[q][j]++;
	q++;
      }
    int n = i;
    int m = j;
    while ((n < B.length) && (m > 0))
      {				//handles the bottom left diagonal
	B[n][m]++;
	n++;
	m--;
      }
    n = i;
    m = j;
    while ((n < B.length) && (m < B.length))
      {				//handles the bottom right diagonal
	B[n][m]++;
	n++;
	m++;
      }
    B[i][j] = 0;
    B[i][0] = 0;
  }
  static void printBoard (int[][]B)
  {
    System.out.print ("(");//beggining of the component form
    int i = 1;//start on row 1
    int j = 0;//column 0 contains all the queens solutions so just print that
    while (i < B.length) //go to the length of the array ie the bottom of the board
      {
	if (i == B.length - 1) //if were on the last row dont put a comma after
	  {
	    System.out.print (B[i][j]);
	  }
	else
	  {
	    System.out.print (B[i][j] + ", "); //otherwise place a comma after
	  }
	i++; //increment to the next row and print
      }
    System.out.print (")");//end component form
    System.out.println ();//print a new line
  }

  static int findSolutions (int[][]B, int i, String mode)
  {
    int x = 0;//will hold all the solutions possible
    //int j = 1;//starting on column 1
    if (i >= B.length)
      {
	if ((mode.compareTo ("verbose")) == 0)//have to compare string using compareTo()
	  {
	    printBoard (B);//handles verbose mode
	  }
	return 1;
      }
    else
      {
	for (int j = 1; j < B.length; j++)//increments through all the columns in row i checking for 0s to place a queen
	  {
	    if (B[i][j] == 0) //if there is a open space for a queen
	      {
		placeQueen (B, i, j);//place the queen
		x += findSolutions (B, i + 1, mode);//go to the next row and start again
		removeQueen (B, i, j);//remove the queens after
	      }
	  }
      }
    return x;//return the number of solutions
  }


  public static void main (String[]args) throws IOException
  {
    String s = "-v";//to compare the command line argument to, to deal with verbose mode
    int size = 0;
    // check number of command line arguments is at least 2
    if ((args.length < 1) || (args.length > 2))//checks to make sure we have args that are size 1 or 2 only (for verbose and regular mode)
      {
	System.out.println ("Usage: Queens [-v] number");
	System.out.println ("Option: -v verbose output, print all solutions");
	System.exit (1);
      }
    try //try catch statements to catch if they try to pass something that isnt int
    {
      if ((s.compareTo (args[0])) == 0)//if we have a -v in args 0 get the int from args 1
	{
	  if (args.length == 1) //prevents an index out of bounds exception if we try to just have a -v, because the next line will access memory not filled in, in that case
	    {
	      System.out.println ("Usage: Queens [-v] number");
	      System.out.println("Option: -v verbose output, print all solutions");
	      System.exit (1);
	    }

	  size = Integer.parseInt (args[1]);//grab the int in args 1 when -v is in 0 (if its not a int will be caught by the catch statement below)
	  int[][] B = new int[size + 1][size + 1];//makes the array args[1]+1
	  int solutions = findSolutions (B, 1, "verbose");//run find solution, to find all possible solutions
	  System.out.println (size + "-Queens has " + solutions +" solutions");
	}
      else //if were not in verbose mode, then the int is in args 0
	{
	  size = Integer.parseInt (args[0]); //grab the int (if its not a int will be caught by the catch statement below
	  int[][] B = new int[size + 1][size + 1];//make a board of size+1 (n+1)
	  int solutions = findSolutions (B, 1, ""); //run the function not in verbose mode
	  System.out.println (size + "-Queens has " + solutions + " solutions");
	}
    }
    catch (NumberFormatException e) //print error message if its not an int
    {
      System.err.println ("Usage: Queens [-v] number");
      System.err.println ("Option: -v verbose output, print all solutions");
      System.exit (1);
   }
  }
}
