# Shayan Salsabilian
# Makefile for FileReverse.c
# 1/29/19
# ssalsabi
# 12B
# Makefile

FileReverse : FileReverse.o
	gcc -o FileReverse FileReverse.o
FileReverse.o : FileReverse.c
	gcc -c -std=c99 -Wall FileReverse.c	
clean :
	rm -f FileReverse FileReverse.o
submit:
	submit cmps012b-pt.w19 lab3 Makefile FileReverse.c README
check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab3/ssalsabi
