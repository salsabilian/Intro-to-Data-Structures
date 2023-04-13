#Shayan Salsabilian
#Makefile for lab7
#3/10/19
#ssalsabi
#12B
#Makefile
#------------------------------------------------------------------------------
# Makefile for any ADT and its test client
#------------------------------------------------------------------------------

JAVAC      = javac
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = DictionaryInterface.java DuplicateKeyException.java KeyNotFoundException.java DictionaryClient.java Dictionary.java  Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)
SUBMIT     = submit cmps012b-pt.w19 lab7


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab7/ssalsabi
 
