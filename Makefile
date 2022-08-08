

all:
	javac DeSerial.java
	javac -cp . Serialme.java
	mkdir -p ser/
	java -cp . Serialme
