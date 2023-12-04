tests: test untest
	@echo "DONE"

compile: serial unserial

ser:
	@echo ">> MAKE SERIAL"
	javac -cp . Serialme.java

unser:
	@echo ">> MAKE UNSERIAL"
	javac DeSerial.java 

unserThis:
	@echo ">> MAKE UNSERIAL"
	javac DeSerialThis.java 


test: ser
	@echo " >> MAKE SERIAL TEST "
	mkdir -p serial 
	java -cp . Serialme 

untest: unser unserThis
	@echo " >> MAKE UNSERIAL TEST "
	bash test_unserialize.sh


clean:
	rm *.class

