//Shayan Salsabilian
//ssalsabi
//3/8/19
//12B
//Implementation file for Dictionary.h
//Dictionary.c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
const int tableSize = 101;
//Node Obj
typedef struct NodeObj
{
	char *key;//items within the NodeObj
	char *value;
	struct NodeObj *next;
}NodeObj;

typedef NodeObj *Node; //rename NodeObj to Node
//newNode()
//constructor of the Node Type
Node newNode(char *key, char *value){
	Node N=malloc(sizeof(NodeObj));//fills in the information needed when we start a new node
	assert(N!=NULL);
	N->key=key;
	N->value=value;
	N->next=NULL;
	return(N);
}
//freeNode()
void freeNode(Node *pN){//frees the memory malloced for the node
	if(pN!=NULL && *pN!=NULL){//checks to make sure the Node was declared and therefore contains a value
		free(*pN);//frees the memory location of the Node
		*pN=NULL;//sets the pointer to NULL, so we cant access that memory location again
	}
}
//DictionaryObj
typedef struct DictionaryObj{//create the basic information for the private class Dictionary
	int numItems;//keeps track of the number of items we had
	Node *S;//creates the array of Nodes
}DictionaryObj;

Dictionary newDictionary(void){//creates a new Dictionary Linked List
	Dictionary M=malloc(sizeof(DictionaryObj));//Malloc the size needed for the dictionary in heap memory
	assert(M!=NULL);//make sure we didn't run out of memory
	M->S=calloc(tableSize,sizeof(Node));//calloc the size needed for the array in heap memory
	assert(M->S!=NULL);//make sure we didn't run out of memory
	M->numItems=0;//start the numItems at 0
	return M;//return the Dictionary 
}

void freeDictionary(Dictionary *pS){
	if(pS!=NULL && *pS!=NULL){//makes sure we declared our dictionary
		if(!isEmpty(*pS))//checks if the Dictionary is empty
			makeEmpty(*pS);//if its not empties it
		free((*pS)->S);//then frees the array of Nodes
		free(*pS);//frees the dictionary
		*pS=NULL;//sets the pointer to the dictionary to NULL
	}
}

int isEmpty(Dictionary D){
	if(D==NULL){//checks to make sure we dont have a null dictionary reference
		fprintf(stderr, "DictionaryError: isEmpty() called on NULL Dictionary Reference\n");
		exit(EXIT_FAILURE);
	}
	return(D->numItems==0);//boolean check if the numItems is 0
}

int size(Dictionary D){
	if(D==NULL){//checks to make sure we dont have a null dictionary reference
		fprintf(stderr, "DictionaryError: size() called on NULL Dictionary Reference\n");
		exit(EXIT_FAILURE);
	}
	return D->numItems;//return the number of items if its not empty
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left (unsigned int value, int shift)
{
	int sizeInBits = 8 * sizeof (unsigned int);
	shift = shift & (sizeInBits - 1);
	if (shift == 0)
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash (char *input)
{
	unsigned int result = 0xBAE86554;
	while (*input)
	{
		result ^= *input++;
		result = rotate_left (result, 5);
	}
	return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1 int
int hash (char *key)
{
	return pre_hash (key) % tableSize;
}

char* lookup(Dictionary D, char *k){//runs through and finds the value for the key given
	if(D==NULL){//checks to make sure the Dictionary was declared
		fprintf(stderr, "DictionaryError: lookup() called on NULL Dictionary Reference\n");
		exit(EXIT_FAILURE);
	}
	int index=hash(k);//find the index needed for the array of nodes based on the key
	Node N=D->S[index];//set Node N to the head of the index needed and found above
	while(N!=NULL){//run through that entire list
		if((strcmp(N->key, k))==0){//if we find a key that matches
			return N->value;//return the value at that node
		}
		N=N->next;//otherwise move to the next item in the list
	}

	return NULL;//if you couldnt find it return a null
}

Node find(Dictionary D, int index, int numItems){//finds the Node needed based on the location in the list given
	if(D==NULL){//checks to see if we have null dictionary reference
		fprintf(stderr, "Dictionary Error: find() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	int i=1;//start the counter at 1
	Node N=D->S[index];//point a Node at the head of the index of the array needed
	while(i<numItems){//go until we reach the number needed
		N=N->next;//move through the list
		i++;//increment the counter
	}
	return N;//return the Node at that location
}

int findIndex(Dictionary D, int index, char *key){//finds the location in the list needed to give to find to find the Node
	int count=0;//start the count at 0
	Node N=D->S[index];//point a node at the head of the index needed
	while(N!=NULL){//go through that entire list
		if((strcmp(key, N->key))==0){//string compare each time until we find the location where the Node needed is
			count++;//increment the count
			return count;//return the count
		}
		count++;//increment the count
		N=N->next;//as you move through the list
	}
	return 0;//if we dont find the key in the list return 0
}

void insert(Dictionary D, char *k, char *v){//insert into the front of the dictionary
	if(D==NULL){//checks to make sure the Dictionary is declared
		fprintf(stderr, "Dictionary Error: insert() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	if((lookup(D, k)!=NULL)){//make sure we haven't already used the key
		fprintf(stderr, "Dictionary Error: insert() called on duplicate key\n");
		exit(EXIT_FAILURE);
	}
	int index=hash(k);//find the index needed from the hash function
	Node N=newNode(k,v);//make a new new Node with the key and value needed
	N->next=D->S[index];//make the next item in that Node everything that was already in the list
	D->S[index]=N;//then make the list equal that Node
	D->numItems++;//increment the number of items
}
void delete(Dictionary D, char *k){
	if(D==NULL){//checks to make sure the Dictionary is declared correctly
		fprintf(stderr, "Dictionary Error: delete() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	if((lookup(D, k)==NULL)){//checks to make sure that the key exists
		fprintf(stderr, "Dictionary Error: delete() called on non-existent key\n");
		exit(EXIT_FAILURE);
	}
	int index=hash(k);//find the index needed from the hash function
	int count=findIndex(D,index,  k);//get the number destination needed for the deletion from the list
	if((strcmp(D->S[index]->key, k))==0){//check if were at the front of the list if we are
		Node P=D->S[index];//point the Node P at the head of the list
		D->S[index]=D->S[index]->next;//then move it to the next item in the list
		freeNode(&P);//and free the Node where the original head was
	}else{
		Node P=find(D, index, count-1);//otherwise go to the Node needed right before the one needed 
		Node N=P->next;//make the deleted item location equal Node N
		P->next=N->next;//make the next item in the Node equal the next item in Node N
		freeNode(&N);//free the Node at location N
	}
	D->numItems--;//decrement the number of items
}
void makeEmpty(Dictionary D){//empty the dictionary
	if(D==NULL){//check to make sure we declared the Dictionary
		fprintf(stderr, "Dictionary Error: makeEmpty() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for(int i=0; i<tableSize; i++){//run through the entire array of Nodes
		while(D->S[i]!=NULL){//run through each list
			delete(D, D->S[i]->key);//delete everything in that specific list
		}
		D->S[i]=NULL;//set the pointer to that list to NULL
	}
	D->numItems=0;//set the number of items to 0
}
void printDictionary(FILE*out, Dictionary D){//print out the Dictionary
	Node N;//declare a Node N
	if(D==NULL){//checks to make sure we declared the Dictionary we want to print out
		fprintf(stderr, "Dictionary Error: printDictionary() called on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for(int i=0; i<tableSize; i++){//run through the entire array of Nodes
		for(N=D->S[i];N!=NULL;N=N->next){//run through each list
			fprintf(out,"%s %s\n", N->key, N->value);//print out each item in that list
		}
	}
}
