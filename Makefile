tests: test untest
	@echo "DONE"

compile: serial unserial

ser:
	@echo ">> MAKE SERIAL"
	javac -cp . Serialme.java

unser:
	@echo ">> MAKE UNSERIAL"
	javac DeSerial.java 

test: ser
	@echo ">> MAKE SERIAL TEST"
	mkdir -p serial 
	java -cp . Serialme 

untest: unser
	@echo ">> MAKE UNSERIAL TEST"
	echo 'find serial/ | grep bin | while read FILENAME ; do echo " **** Unserialize @{FILENAME}"; java DeSerial @{FILENAME}; done' | tr "@" "$$" | bash


clean:
	rm *.class

