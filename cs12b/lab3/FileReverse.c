//Shayan Salsabilian
//ssalsabi
//1/24/19
//12B
//Reverse a string, but in c
//FileReverse.c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void stringReverse (char *s)
{
  int i = 0;
  int j = strlen (s) - 1;
  char temp;
  while (i < j)
    {
      temp = s[i];
      s[i] = s[j];
      s[j] = temp;
      i = i + 1;
      j = j - 1;
    }
}

int main (int argc, char *argv[])
{
  FILE *in;			//file where the information is stored
  FILE *out;			//file being sent out to
  char word[256];		//array holding the words in file in for me to manipulate

  if (argc != 3)
    {				//error check to make sure we have exactly three arguments so that we can manipulate the words
      printf ("Usage: %s <input file> <output file>\n", argv[0]);	//
      exit (EXIT_FAILURE);	//then exit
    }

  in = fopen (argv[1], "r");	//opens the file in argument 1 save it in in
  if (in == NULL)
    {				//if we have an empty file print an error message
      printf ("Unable to read from file %s\n", argv[1]);
      exit (EXIT_FAILURE);	//then exit
    }

  out = fopen (argv[2], "w");	//writes to file out
  if (out == NULL)
    {				//if out file doesnt exit print an error message
      printf ("Unable to write to file %s\n", argv[2]);
      exit (EXIT_FAILURE);	//then exit
    }

  while (fscanf (in, " %s", word) != EOF)
    {
     stringReverse (word);	//saves the in file words into the word array
      fprintf (out, "%s\n", word);	//the prints it to the out file
    }

  fclose (in);			//close both files
  fclose (out);

  return (EXIT_SUCCESS);	//main needs a return in c

}

