//Shayan Salsabilian
//ssalsabi
//2/3/19
//12B
//Count the different types in each line of a file
//charType.c

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100 //the amount of memory allocated for the arrays will never go passed this

void extract_chars(char* s, char* a,char *d, char *p, char *w);//function prototype

int main(int argc, char* argv[]){
	FILE* in; //handle for input file
	FILE* out; //handle for output file
	char*line; //string holding input line
	char* alpha_num; //string holding all alpha-num chars
	char* d; //digits
	char* p; //punctuation
	char* w; //whitespaces

	//check command line for correct numbers of arguments
	if(argc != 3){
		printf("Usage: %s input-file output-file\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	//open input file for reading
	if((in=fopen(argv[1], "r"))==NULL){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	//open output file for writing
	if((out=fopen(argv[2], "w"))==NULL){
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	//allocate strings line and alpha_num on the heap
	line= calloc(MAX_STRING_LENGTH+1, sizeof(char));//allocating an array the size of the pointer to the file input lines which wont go over 100 chars
	alpha_num=calloc(MAX_STRING_LENGTH+1, sizeof(char));
	d=calloc(MAX_STRING_LENGTH+1, sizeof(char));
	p=calloc(MAX_STRING_LENGTH+1, sizeof(char));
	w=calloc(MAX_STRING_LENGTH+1, sizeof(char));
	assert(line!=NULL && alpha_num!=NULL &&d!=NULL && p!=NULL && w!=NULL); //makes sure calloc didnt fail, add others after

	//read each line in input file, extract all types from the input file
	int linecount=1; //holds the line count
	char *plurala="";//These are for the determinance of plural or singular in the code
	char *plurald="";
	char *pluralp="";
	char *pluralw="";
	while( fgets(line, MAX_STRING_LENGTH, in)!=NULL){ //will grab line by line from the input file
		extract_chars(line, alpha_num, d, p, w); //running the program to find the number of whitespaces, punctuations, digits, and alphabetical characters in each line
		if((strlen(alpha_num))!=1){ //if there is more than one whitespaces, punctuations, digits, or alphabetic charaters fill the plural variable of one of them to "s"
		plurala="s";
		}
		if((strlen(d))!=1){
		plurald="s";
		}
		if((strlen(p))!=1){
		pluralp="s";
		}
		if((strlen(w))!=1){
		pluralw="s";
		}
		fprintf(out, "line %d contains:\n", linecount); //tells us the line were on
		fprintf(out, "%ld alphabetic character%s: %s\n",strlen(alpha_num),plurala, alpha_num);//tells us the number of alphabetic characters
		fprintf(out, "%ld numeric character%s: %s\n", strlen(d),plurald, d); //tells us the number of digits
		fprintf(out, "%ld punctuation character%s: %s\n", strlen(p),pluralp, p);//tells us the number of punctuation characters
		fprintf(out, "%ld whitespace character%s: %s\n", strlen(w),pluralw, w);//tells us the number of whitespace characters
		linecount=linecount+1;//increase the linecounts
		plurala="";//wipe the plurals
		plurald="";
		pluralp="";
		pluralw="";
	}

	// free heap memory
	free(line);
	free(alpha_num);
	free(d);
	free(p);
	free(w);

	//close input and output files
	fclose(in);
	fclose(out);

	return EXIT_SUCCESS;
}

void extract_chars(char*s, char*a, char*d, char*p, char*w){
	int i=0,j=0; //counters
	while(s[i]!='\0' && i<MAX_STRING_LENGTH){//until we reach the end of null or until i reaches the max string length
		if(isalpha( (int) s[i])) a[j++]= s[i]; //check if we have an alphabetical characters, if it is place it in the a pointer
		i++; //increment the counter
	}
	a[j] = '\0'; //place a null at the end, so old characters dont go into the new array
	i=0;//reset i and j for the next check of digits
	j=0;
	while(s[i]!='\0' && i<MAX_STRING_LENGTH){ //until we reach the end of null or until i reaches the max string length
	if(isdigit( (int) s[i]))  d[j++]=s[i];//check if we have a number, if we do place it in the d pointer
	i++;//increment the counter
	}
	d[j]='\0';//place a null at the end, so old characters dont go into the new array
	i=0; //reset i and j for the next check of punctuations
	j=0;
	while(s[i]!='\0' && i<MAX_STRING_LENGTH){//until we reach the end of null or until i reaches the max strig length
	if(ispunct( (int) s[i])) p[j++]=s[i];//check if we have a punctuation characters, if it is place it in the p pointer
	i++;//increment the counter
	}
	p[j]='\0';//place a null at the end, so old characters dont go into the new array
	i=0;//reset i and j for the next check of whitespaces
	j=0;
	while(s[i]!='\0' && i<MAX_STRING_LENGTH){ //until we reach the end of null or until i reaches the max string length
	if(isspace( (int) s[i])) w[j++]=s[i]; //check if we have a whitespace characters, if we do place it in the w pointer
	i++;//increment counter
	}
	w[j]='\0';//place a null at the end, so old characters dont go into the new array
	}
