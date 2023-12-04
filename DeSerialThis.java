
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.management.MalformedObjectNameException;

import java.lang.reflect.*;  


class SomeSecretClass implements Serializable {
  public Object magicField;
  public SomeSecretClass(){ 
    this.magicField = "secretVallue1337"; 
  }
}


class DeSerialThis {
  

  public static void main(String[] args) throws FileNotFoundException, IOException, MalformedObjectNameException,  ClassNotFoundException  {
      System.out.println("Working on it ... "); 


      ObjectInputStream ois;
      ois = new ObjectInputStream(new FileInputStream(args[0]));
      SomeSecretClass obj = ois.readObject();
      ois.close();

      System.out.println("Read object :");
      System.out.println(obj);
      System.out.println("Value :");
      System.out.println(obj.magicField);

    }
}
