# Shayan Salsabilian
# Makefile for charType.c
# 2/3/19
# ssalsabi
# 12B
# Makefile

charType : charType.o
	gcc -o charType charType.o
charType.o : charType.c
	gcc -c -std=c99 -Wall charType.c
clean:
	rm -f charType charType.o
submit:
	submit cmps012b-pt.w19 lab4 Makefile charType.c README
check:
	valgrind --leak-check=full charType infile outfile
check2:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab4/ssalsabi
