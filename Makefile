

all:
	javac DeSerial.java
	javac -cp psemf.jar Serialme.java
	mkdir -p ser/
	java -cp psemf.jar:. Serialme
