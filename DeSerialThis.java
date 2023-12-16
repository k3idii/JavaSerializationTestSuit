
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.io.Serializable; 
import javax.management.MalformedObjectNameException;

import java.lang.reflect.*;  


class SomeSecretClass implements Serializable {
  public Object username;
  public SomeSecretClass(){ 
    this.username = "secretVallue1337"; 
  }
}

class DeSerialThis {
  public static void main(String[] args) throws FileNotFoundException, IOException, MalformedObjectNameException,  ClassNotFoundException  {
      System.out.println("Working on it ... "); 
      ObjectInputStream ois;
      ois = new ObjectInputStream(new FileInputStream(args[0]));
      Object obj = ois.readObject();
      ois.close();
      SomeSecretClass o1 = (SomeSecretClass)obj;
      System.out.println("Read object :");
      System.out.println(o1);
      System.out.println("Value :");
      System.out.println(o1.username);
      if (o1.username == "admin") {
        System.out.println(" GOOD USERNAME. U CAN HAZ FLAG");
      } else {
        System.out.println("invalid USERNAME ");
      }

    }
}


