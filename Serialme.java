
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

class EX implements Externalizable {

  private static final long serialVersionUID = 31337000L;
  private String name;
  private int code;
  private AA obj1;
  
  public EX(){  obj1=new AA(123); } 
  public EX(String a, int b){ name=a; code=b; obj1=new AA(123); }
  
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
      out.writeUTF(name);
      out.writeInt(code);
      out.writeObject(obj1);
      System.out.println("CUSTOM WRITER END");
  }

  @Override
  public void readExternal(ObjectInput in) 
    throws IOException, ClassNotFoundException {
      this.name = in.readUTF();
      this.code = in.readInt();
      this.obj1 = (AA)in.readObject();
      System.out.println("CUSTOM READER END");
      System.out.println("Value=" + obj1.obj);
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
    
    int i;

    serialize_to_file(
      "serial/str_test123.bin", 
      new String("test123")
    );
    
    serialize_to_file(
      "serial/int_99.bin",
      99
    ); // new Integer()
    
    serialize_to_file(
      "serial/bytea_arr.bin",   
      (new String("test123")).getBytes()
    );

    serialize_to_file(
      "serial/inh_obj.bin", 
      new BB(0xaaaa,0xbbbb) 
    );

    AA[] objArr = new AA[10];
    for (i=0;i<10;i++){ objArr[i] = new AA(0x100 + i); }

    serialize_to_file(
      "serial/arr_of_obj.bin", 
      new BOX(objArr) 
    );

    EnumStuff tmp1 = EnumStuff.VALUE2;
    serialize_to_file(
      "serial/enum.bin",
      new BOX(tmp1)
    );


    EX tmp2 = new EX("foo", 123);
    serialize_to_file(
      "serial/obj_external.bin",
      tmp2
    );


  }
}
