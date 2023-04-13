//Shayan Salsabilian
//ssalsabi
//3/12/19
//12B
//Tests for Dictionary.c
//DictionaryTest.c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

int main(){
	Dictionary A=newDictionary();//create a new Dictionary
	printf("Beginning isEmpty Tests:\n");
	int check=isEmpty(A);//check if the Dictionary is empty when it should be
	printf("	Does isEmpty() handle an empty list correctly?\n");
	if(check==1){//if it is print Passed otherwise print Failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	insert(A, "1", "A");//insert into the Dictionary 
	check=isEmpty(A);//then check if the Dictionary is not empty which it should be
	printf("	Does isEmpty() handle a non empty list correctly?\n");
	if(check==0){//if it is print Passed otherwise print failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	printf("Beginning size Tests:\n");
	printf("	Does size() handle correctly when there is one item in the list?\n");
	check=size(A);//get the size, should be 1
	if(check==1){//if it is print passed otherwise print failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	} 
	insert(A, "23", "6");//insert two more items into the list
	insert(A, "T", "ME");
	check=size(A);//check the size again should be 3
	printf("	Does size() handle correctly when there are multiple items in the list?\n");
	if(check==3){//if it is print passed otherwise print failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	printf("Beginning lookup Tests:\n");
	char* check1=lookup(A, "23");//use lookup to find the value needed from the key, should be 6
	printf("	Does lookup() return the right value in the middle of the list?\n");
	if((strcmp(check1, "6"))==0){//if it is print Passed otherwise print Failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	check1=lookup(A, "T"); //use lookup to find the value needed from the key, should be ME
	printf("	Does lookup() return the right value at the end of the list?\n");
	if((strcmp(check1, "ME"))==0){//if it is print Passed otherwise print Failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	printf("Beginning insert Tests:\n");
	printf("	Does insert() work correctly at the end of the list?\n");
	check1=lookup(A, "1");//lookup the value for the key in the Dictionary
	if((strcmp(check1, "A"))==0){//if it matches, then we inserted properly
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	insert(A, "ABC", "123");//insert an item at the front of the list
	check1=lookup(A, "ABC");//make sure it was properly inserted
	printf("	Does insert() work correctly at the front of the list?\n");
	if((strcmp(check1, "123"))==0){//if it matches, then we inserted properly
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	/*printf("	The following test should produce an error:\n");
	  insert(A, "ABC", "NOTIMPORTANT");*/
	printf("Beginning delete Tests:\n");
	printf("	Does delete() delete properly from the front of the list?\n");
	delete(A, "ABC");//delete the first item in the list
	check1=lookup(A, "ABC");//lookup the value of the key, it should be null
	if(check1==NULL){//if it is print Passed, otherwise print Failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	printf("	Does delete() properly from the back of the list?\n");
	delete(A, "1");	//delete the last item in the list
	check1=lookup(A, "1");//lookup the value of the key, it should be null
	if(check1==NULL){//if it is print Passed, otherwise print Failed
		printf("		Passed\n");
	}else{
		printf("		Failed\n");
	}
	/*printf("	The following test should produce an error\n");
	  delete(A, "NOTIMPORTANT");*/
	printf("Beginning makeEmpty Tests:\n");
	makeEmpty(A);//empty the list
	printf("	Does makeEmpty make the number of items in the list 0 correctly?\n");
	if((size(A))==0){//check to see if the number of items is now 0, if it is print passed otherwise print failed
	printf("		Passed\n"); 
	}else{
	printf("		Failed\n");
	}
	printf("Beginning print Tests:\n");
	insert(A, "1", "12");//insert three items into the list
	insert(A, "key", "value");
	insert(A, "3", "28");
	printf("The following test should print:\n");
	printf("key value\n");
	printf("3 28\n");
	printf("1 12\n");
	printf("output:\n");
	printDictionary(stdout, A);//print out the list
	return 0;
}
