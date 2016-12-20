all: ClassB
	java packageb.ClassB

ClassB: packageb/ClassB.java
	javac packageb/ClassB.java

clean:
	rm -rf package{a,b}/*{.class,~}
