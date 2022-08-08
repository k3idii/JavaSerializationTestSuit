
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


// Your First Program

class BOX implements Serializable {
  Object field1;
  public BOX(Object x){ this.field1 = x ; }
}

class AA implements Serializable { 
    int AAfield; 
    Object obj;
    public AA(int i){ this.AAfield = i; this.obj = new String("abc123"); } 
} 

class BB extends AA{ 
    int BBfield; 
    public BB(int i, int j) {super(i); this.BBfield = j;  } 
} 



class Serialme {


  public enum EnumStuff {
    VALUSE1, VALUE2, VALUE3
  }

  private static void serialize_to_file(String fn,Object o) throws FileNotFoundException, IOException, MalformedObjectNameException  {
    
    System.out.println(String.format("Serialize [class: '%s'] -> %s",o.getClass().getName(),fn));
    ObjectOutputStream out;
    out = new ObjectOutputStream( new FileOutputStream(fn));
    out.writeObject(o);
    out.flush();
    out.close();
  }
    


  public static void main(String[] args) throws FileNotFoundException, IOException, MalformedObjectNameException  {
      System.out.println("Working on it ... "); 
    
    int i;

    serialize_to_file(
      "ser/str_test123.bin", 
      new String("test123")
    );
    
    serialize_to_file(
      "ser/int_99.bin",
      99
    ); // new Integer()
    
    serialize_to_file(
      "ser/bytea_arr.bin",   
      (new String("test123")).getBytes()
    );

    serialize_to_file(
      "ser/inh_obj.bin", 
      new BB(0xaaaa,0xbbbb) 
    );

    AA[] objArr = new AA[10];
    for (i=0;i<10;i++){ objArr[i] = new AA(0x100 + i); }

    serialize_to_file(
      "ser/arr_of_obj.bin", 
      new BOX(objArr) 
    );

    EnumStuff tmp1 = EnumStuff.VALUE2;
    serialize_to_file(
      "ser/enum.bin",
      new BOX(tmp1)
    );




  }
}
