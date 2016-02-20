JCC = javac
JFLAGS = -g
default: CompleteGraph.class MinHeap.class randmst.class
CompleteGraph.class: CompleteGraph.java
	$(JCC) $(JFLAGS) CompleteGraph.java
MinHeap.class: MinHeap.java
	$(JCC) $(JFLAGS) MinHeap.java
randmst.class: randmst.java
	$(JCC) $(JFLAGS) randmst.java
clean:
	$(RM) *.class
