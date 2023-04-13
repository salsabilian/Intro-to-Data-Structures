#Shayan Salsabilian
#ssalsabi
#1/24/19
#12B
#Makefile
#Creates the class and jar file for Queens.java
#------------------------------------------------------------------------------
#Makefile
#------------------------------------------------------------------------------
JAVASRC = Queens.java
SOURCES =Makefile $(JAVASRC)  README
MAINCLASS = Queens
CLASSES = Queens.class
JARFILE = Queens
SUBMIT = submit cmps012b-pt.w19 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest	
	 jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	 javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa2/ssalsabi

