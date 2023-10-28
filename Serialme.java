
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
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.ObjectInput;

import javax.management.MalformedObjectNameException;


// class that serves as container
class C001 implements Serializable {
  Object C001field1;
  public C001(Object x){ 
    this.C001field1 = x ; 
  }
}

class C002Base implements Serializable { 
    int C002intField1; 
    Object C002objField2;
    public C002Base(int i){ 
      this.C002intField1 = i; 
      this.C002objField2 = new String("stringFieldValue123");
    } 
} 

class C003Child extends C002Base{ 
    int C003intField1; 
    public C003Child(int i, int j) {
      super(i); 
      this.C003intField1 = j;  
    } 
} 

class EX implements Externalizable {

  private static final long serialVersionUID = 31337000L;
  private String name;
  private int code;
  private C002Base obj1;
  
  public EX(){  obj1=new C002Base(123); } 
  public EX(String a, int b){ name=a; code=b; obj1=new C002Base(123); }
  
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
      System.out.println("> CUSTOM WRITER START");
      out.writeUTF(name);
      out.writeInt(code);
      out.writeObject(obj1);
      System.out.println("< CUSTOM WRITER END");
  }

  @Override
  public void readExternal(ObjectInput in) 
    throws IOException, ClassNotFoundException {
      System.out.println("> CUSTOM READER START");
      this.name = in.readUTF();
      this.code = in.readInt();
      this.obj1 = (C002Base)in.readObject();
      System.out.println("< CUSTOM READER END");
      System.out.println("Value=" + obj1.C002objField2);
  }
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
    
    
    serialize_to_file(
      "serial/str_test123.bin", 
      new String("test123")
    );
    
    serialize_to_file(
      "serial/int_99.bin",
      99
    );
    
    serialize_to_file(
      "serial/bytes_arr.bin",   
      (new String("test123")).getBytes()
    );

    serialize_to_file(
      "serial/child_obj3.bin", 
      new C003Child(0xaaaa,0xbbbb) 
    );

    int i;
    C002Base[] objArr = new C002Base[10];
    for (i=0;i<10;i++){ objArr[i] = new C002Base(0x100 + i); }

    serialize_to_file(
      "serial/arr_of_obj2.bin", 
      new C001(objArr) 
    );

    EnumStuff tmp1 = EnumStuff.VALUE2;
    serialize_to_file(
      "serial/obj_enum.bin",
      new C001(tmp1)
    );

    EX tmp2 = new EX("foo", 123);
    serialize_to_file(
      "serial/obj_externalizable.bin",
      tmp2
    );

    Object ex1 = new RuntimeException("fooo");
    serialize_to_file(
      "serial/obj_exception.bin",
      ex1
    );

  }
}
