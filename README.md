# JavaSerializationTestSuit
Stuff to test Java (de)serialization

## Usage :

### Serialize various structures :
```
# javac -cp . Serialme.java
# mkdir -p ser/
# java -cp . Serialme

# find ser/
  ser/
  ser/str_test123.bin
  ser/bytea_arr.bin
  ser/arr_of_obj.bin
  ser/inh_obj.bin
  ser/int_99.bin
  ser/enum.bin


```

### Unserialize :
```
# javac DeSerial.java
# java -cp . DeSerial ser/str_test123.bin 
Working on it ... 
test123
```
