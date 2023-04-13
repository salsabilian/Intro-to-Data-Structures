#Shayan Salsabilian
#ssalsabi
#3/14/19
#12B
#Makefile for DictionaryClient.c
#Makefile
#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

ADT     = Dictionary
EXEBIN  = $(ADT)Client
SOURCES = $(ADT).c $(EXEBIN).c
OBJECTS = $(ADT).o $(EXEBIN).o
HEADERS = $(ADT).h

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)

submit:
	submit cmps012b-pt.w19 pa5 README Dictionary.c Dictionary.h DictionaryTest.c DictionaryClient.c Makefile 

check1:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa5/ssalsabi
