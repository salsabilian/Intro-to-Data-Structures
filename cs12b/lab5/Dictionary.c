//Shayan Salsabilian
//ssalsabi
//2/19/19
//12B
//Implementation file for Dictionary.h
//Dictionary.c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj
{
  char *key;			//creates a key
  char *value;			//creates a value
  struct NodeObj *next;		//includes a next Obj in the linked list
} NodeObj;

// Node
typedef NodeObj *Node;		//makes the NodeObj a node

// newNode()
// constructor of the Node type
Node
newNode (char *key, char *value)
{				//makes a newNode with a specific key and value needed
  Node N = malloc (sizeof (NodeObj));	//allocate an amount of memory to hold the NodeObj
  assert (N != NULL);		//if we have a null malloc return null
  N->key = key;			//place the key in the key area of the node object
  N->value = value;		//place the value in the value area of the node object
  N->next = NULL;		//make the next item the null
  return (N);			//return the node
}

// freeNode()
// destructor for the Node type
void
freeNode (Node * pN)
{
  if (pN != NULL && *pN != NULL)
    {				//make sure the node isn't already empty 
      free (*pN);		//if its not free the node
      *pN = NULL;		//then set its pointer equal to null so we cant find it
    }
}

// DictionaryObj
typedef struct DictionaryObj
{				//create a DictionaryObj
  Node head;			//make the head node that will point to the front of the list
  int numItems;			//keep track of the number of items
} DictionaryObj;


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
// parameters: none
// post: Dictionary S
Dictionary
newDictionary (void)
{				//create a new dictionary
  Dictionary S = malloc (sizeof (DictionaryObj));	//make a Dictionary that allocates memory for the size of the DictionaryObj
  assert (S != NULL);		//check for malloc failures
  S->head = NULL;		//set the head to null
  S->numItems = 0;		//set the numItems to 0
  return S;			//return the dictionary
}

// freeDictionary()
// destructor for the Dictionary type
// parameters: Dictionary pS
// post: none
void
freeDictionary (Dictionary * pS)
{				//free the entire dictionary
  if (pS != NULL && *pS != NULL)
    {				//check to make sure its not a null dictionary
      if (!isEmpty (*pS))	//if the dictionary is not empty then empty it
	makeEmpty (*pS);
      free (*pS);		//free the dictionary
      *pS = NULL;		//set the pointer to null
    }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// parameters: none
// post: 1 if numItems is 0, and 0 if numItems is 1
int
isEmpty (Dictionary S)
{
  if (S == NULL)
    {				//if null print a null dictionary reference error
      fprintf (stderr,
	       "Dictionary Error: isEmpty() called  on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }
  return (S->numItems == 0);	//otherwise return one if the size is 0 and 0 otherwise
}

// size()
//returns number of items
//parameters: none
//post: numItems
int
size (Dictionary D)
{
  if (D == NULL)
    {
      fprintf
	(stderr,
	 "Dictionary Error: size() called on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }
  return D->numItems;		//just checks the number of items and returns it
}

//lookup()
//returns the value v such that (k,v) is in D, or returns null if not
//parameters:Dictionary D, char* k
//post: value contained char*
char *
lookup (Dictionary D, char *k)
{				//lookup the value needed for the key
  if (D == NULL)
    {				//if the dictionary pointer is null return a NULL dictionary reference error
      fprintf (stderr,
	       "Dictionary Error: lookup() called on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }				//otherwise
  Node N = D->head;		//save the start of the dictionary into Node N
  while (N != NULL)
    {				//then go until the end of the dictionary
      if ((strcmp (N->key, k)) == 0)
	{			//then string compare till we find the identical key
	  return N->value;	//then return the value when the key is identical
	}
      N = N->next;		//otherwise go to the next node in the dictionary
    }
  return NULL;			//if we can't find it return a null
}

//find() (private function)
//parameters:Dictionary D ,number of items in Dictionary
//post: node at the number of items
//finds the last node on the list
Node
find (Dictionary D, int numItems)
{
  if (D == NULL)
    {				//if the dictionary pointer is null return a NULL dictionary reference error
      fprintf (stderr,
	       "Dictionary Error: find() called on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }
  int i = 1;			//start the counter at 1
  Node N = D->head;		//start at the head of the dictionary
  while (i < numItems)
    {				//go till we reach the number of items specified
      N = N->next;
      i++;
    }
  return N;			//once there return the node your at
}

//count() (private function)
//parameters: Dictionary D, key
//post: index
//find the index where the  key value is held
int
findIndex (Dictionary D, char *key)
{
  int count = 0;		//start the count
  Node N = D->head;		//put the head of the function in the Node
  while (count <= D->numItems)
    {				//increment through the Dictionary 
      if ((strcmp (key, N->key)) == 0)
	{			//when you find the key
	  count++;
	  return count;		//return the count
	}
      count++;
      N = N->next;		//if not move onto the next item on the list
    }
  return 0;			//if nothing is found return 0
}

//insert()
//parameters: Dictionary D, k, v
//post: none
//inserts new(key, value) into D
void
insert (Dictionary D, char *k, char *v)
{
  if (D == NULL)
    {				//if the dictionary pointer is null return a NULL dictionary reference error
      fprintf (stderr,
	       "Dictionary Error: insert() called on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }
  if ((lookup (D, k)) != NULL)
    {				//check to make sure we dont already have the key
      fprintf (stderr, "Dictionary Error: insert called on duplicate key\n");
      exit (EXIT_FAILURE);	//if we do produce the error and exit
    }
  if (D->numItems == 0)
    {				//if were at the start of the dictionary
      Node N = newNode (k, v);	//create a new node at the front
      D->head = N;		//then add it at the front of the dictionary
    }
  else
    {
      Node P = find (D, D->numItems);	//find the end of the dictionary
      P->next = newNode (k, v);	//add a new node right after it
      P = P->next;		//then move the node to that location so the new node is the end of the dictionary
    }
  D->numItems++;
}

//delete()
//parameters: Dictionary D, k
void
delete (Dictionary D, char *k)
{
  if (D == NULL)
    {				//check for a null dictionary reference
      fprintf (stderr,
	       "Dictionary Error: delete() called on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }
  if (lookup (D, k) == NULL)
    {				//make sure we are deleting a key
      fprintf (stderr,
	       "Dictionary Error: delete() called on non-existant key:\n");
      exit (EXIT_FAILURE);
    }
  int count = findIndex (D, k);	//find the index needed
 // if ((D->numItems == 1) && (strcmp (D->head->key, k) == 0))
   // {				//if the head is the only item in the list
     // free (D->head);		//just free the head
     // D->head = NULL;		//and point the pointer to NULL
   // }
 // else
 //   {				//otherwise the list is larger than 1
      if (strcmp (D->head->key, k) == 0)	//check if were at the start of the list
	{
	  Node P = D->head;	//set Node P to the head
	  D->head = D->head->next;	//move the head to the next node
	  freeNode (&P);	//free the old head
	}
      else
	{
	  Node P = find (D, count - 1);	//find the Node right before the one we need to delete
	  Node N = P->next;	//set the Node N to where the item we want to delete is
	  P->next = N->next;	//make the next item in the Node P to the next item in Node N, therefore getting rid of the item we need to delete
	  freeNode (&N);	//free the target Node
	}
   // }
  D->numItems--;		//reduce the number of items
}

//makeEmpty()
////returns Dictionary to empty state
////parameters: Dictionary D
////post: none
void
makeEmpty (Dictionary D)
{
  if (D == NULL)
    {				//check for a null dictionary reference
      fprintf (stderr,
	       "Dictionary Error: makeEmpty() called on NULL Dictionary reference\n");
      exit (EXIT_FAILURE);
    }
 /* if (D->numItems == 0)
    {				//if we already have an empty dictionary then produce an empty dictionary error
      fprintf (stderr,
	       "Dictionary Error: makeEmpty() called on empty Dictionary\n");
      D->head = NULL;
      free (D);
      exit (EXIT_FAILURE);
    }*/
  while (D->numItems > 0)
    {				//delete nodes until the number of items is 0
      delete (D, D->head->key);
    }
  D->head = NULL;		//set the head to null
  D->numItems = 0;		//set the number of items to 0
}



// printStack()
// prints a text representation of S to the file pointed to by out
// pre: none
void
printDictionary (FILE * out, Dictionary S)
{
  Node N;			//create a new node
  if (S == NULL)
    {				//check for a null dictionary reference error
      fprintf (stderr,
	       "Dictionary Error: printDictionary() called on NULL Dictionary reference\n");

      exit (EXIT_FAILURE);
    }
  for (N = S->head; N != NULL; N = N->next)
    fprintf (out, "%s %s\n", N->key, N->value);	//if not go from the head of the dictionary to the end of the dictionary printing the key and value with a newline
}
