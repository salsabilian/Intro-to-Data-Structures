//FileReverse.java
//Shayan Salsabilian
//ssalsabi
//1/24/19
//12B
//Reverses the first n characters of s in a file found through the command prompt

import java.io.*;
import java.util.Scanner;

class FileReverse
{
	public static String stringReverse (String s, int n)
	{
		String temp=""; //creates a temporary string that will hold the reversal of string s
		if(n>0){ //when n is not 0 
			temp=""+s.charAt(n-1);//take the nth letter and put it in temp
			temp=temp+stringReverse(s, n-1);//adds the next  nth letter to temp	
		}
		return temp;//return the reversed part of the string

	}
	public static void main (String[]args) throws IOException
	{

		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
		int i, n, linenumber = 0;

		//Checks number of command line arguments is atleast 2
		if (args.length < 2)
		{
			System.out.println ("Usage: FileReverse <input file> <output file>");
			System.exit (1);
		}

		//opens the files
		try{//catches the error message when the file doesnt exit
			in = new Scanner (new File (args[0]));
			out = new PrintWriter (new FileWriter (args[1]));
			in.useDelimiter ("\\Z");//matches the end of file characters
			String s = in.next ();	//reads the whole file in as a string
			in.close ();
			token = s.split ("\\s+");	//splits line around white spaces
			for (i = 0; i <token.length; i++) //uses a for loop to reverse  each part of the in file one at a time
			{
				token[i]=stringReverse(token[i], token[i].length()); //will reverse the token
				out.println(token[i]);//will print the reversed token to the file
			}
			out.close();
		}
		catch(FileNotFoundException e){ //appends the error message to this instead
			System.out.println(e.getMessage());
			System.out.println("Usage: FileReverse <input file> <output file>");
			System.exit(1);//then exits the program
		}
	}
}
