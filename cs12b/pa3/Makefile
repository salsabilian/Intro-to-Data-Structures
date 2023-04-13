#Shayan Salsabilian
#Makefile for Dictionary.java, DictionaryTest.java, DuplicateKeyException.java, KeyNotFoundException.java, DictionaryInterface.java, DictionaryClient.java
#2/5/19
#ssalsabi
#12B
#Makefile

JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT     = submit cmps012b-pt.w19 pa3


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
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa3/ssalsabi
