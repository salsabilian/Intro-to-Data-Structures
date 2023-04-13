//Shayan Salsabilian
//ssalsabi
//2/19/19
//12B
//Tests the basic functions of our Dictionary
//DictionaryTest.c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
int
main ()
{
  Dictionary A = newDictionary ();	//creating a new Dictionary
  printf ("Beginning isEmpty() Tests:\n");
  int check = isEmpty (A);	//checking if the Dictionary is empty when it should be
  printf ("	Does isEmpty() handle an Empty Dictionary Correctly?\n");
  if (check == 1)
    {
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  char *key = "ABC";		//first key
  char *value = "123";		// first value
  insert (A, key, value);	//inserting first item in the dictionary
  printf ("	Does isEmpty() handle a non-Empty Dictionary Correctly?\n");
  check = isEmpty (A);		//checking to see the dictionary is not empty
  if (check == 0)
    {
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  printf ("Beginning size() Tests:\n");
  delete (A, "ABC");		//deleting first pair in the dictionary
  printf ("	Does size() return a empty Dictionary Correctly?\n");
  check = size (A);		//run the function size 
  if (check == 0)
    {				//size is 0
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  insert (A, key, value);	//insert new key and value
  check = size (A);		//the size should now be 1
  printf ("	Does size() return a Dictionary of size 1 Correctly\n");
  if (check == 1)
    {				//check to make sure the size is 1
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  printf ("Beginning lookup() Tests:\n");
  char *check1;			//string checker
  check1 = lookup (A, "ABC");	//looking up the value for key "ABC"
  printf ("	Does lookup() find the correct value for a key?\n");
  if ((strcmp (check1, "123")) == 0)
    {				//compare the value with the value it should be
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  check1 = lookup (A, "1");	//looking up a nonexistent key
  printf ("	Does lookup() return a NULL when we don't have a key?\n");
  if (check1 == NULL)
    {				//checking if the nonexitent key returns a null
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  printf ("Beginning insert() Tests:\n");
  printf
    ("	Does insert() insert correctly at the beginning of the Dictionary?\n");
  check1 = lookup (A, "ABC");	//does lookup return the correct value for the first place in the dictionary 
  if ((strcmp (check1, "123")) == 0)
    {
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  printf
    ("	Does insert() insert correctly to the second part in the list?\n");
  key = "Code";			//does insert return the correct value for the second place in the dictionary
  value = "Bug";
  insert (A, key, value);
  check1 = lookup (A, "Code");
  if ((strcmp (check1, "Bug")) == 0)
    {
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  /*printf("      Does insert() produce an error when trying to enter a key we already used?\n");
     insert(A, key, value); */
  printf ("Beginning delete() Tests:\n");
  printf ("	Does delete() from the start of the Dictionary Correctly?\n");
  delete (A, "ABC");		//deleting the first key
  check1 = lookup (A, "ABC");	//looking up the key should return a null
  if (check1 == NULL)
    {				//checks to make sure we return a null
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  insert (A, "1", "q");		//inserting items into the dictionary
  insert (A, "5", "t");
  printf
    ("	Does delete() from the third place in the dictionary correctly?\n");
  delete (A, "5");		//deleting the last item
  check1 = lookup (A, "5");	//looking to see if we get a null
  if (check1 == NULL)
    {				//should return a null
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  /*printf("      Does delete() produce an error when trying delete a non-existant key\n");
     delete(A,"Bangladesh"); */

  printf ("Beginning makeEmpty() Tests:\n");
  printf ("	Does makeEmpty make the number of items equal to zero?\n");
  makeEmpty (A);		//Empty the dictionary
  int i = size (A);		//get the size back
  if (i == 0)
    {				//the size should be 0
      printf ("		Passed\n");
    }
  else
    {
      printf ("		Failed\n");
    }
  printf ("Beginning printDictionary() Tests:\n");
  printf ("The following test should output:\n");
  printf ("1 A\n");
  printf ("2 B\n");
  printf ("3 C\n");
  printf ("output:\n");
  insert (A, "1", "A");		//insert three items into Dictionary A
  insert (A, "2", "B");
  insert (A, "3", "C");
  printDictionary (stdout, A);	//then print it out
  freeDictionary (&A);		//free the dictionary
  return 1;
}
