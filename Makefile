JFLAGS = -g
JC = javac
JD = javadoc -private -charset utf-8

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        MetodoMontante.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
