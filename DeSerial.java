
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



class DeSerial {
  

  static boolean has_ToString(Object object) { // throws InvocationTargetException, IllegalArgumentException, IllegalAccessException {
    Class c1 = object.getClass();
    // System.out.println( c1 );
    try  {
      Class c2 = c1.getMethod("toString").getDeclaringClass();
      // System.out.println( c2 );
      if ( c1 == c2 ) {
        return true;
      }
      // else : parent class 
    } catch ( NoSuchMethodException err ) {
      // not toString
    }
    return false;
}


  static void recur_print_object_field(Object obj, int indent){
    String ind_str = ".  ".repeat(indent);

    System.out.print(ind_str);
    System.out.print( "CLASS : ");
    System.out.print( obj.getClass().getName() );
    System.out.print( " / " );
    System.out.print( obj.getClass().getSimpleName() ); 
    System.out.println( " ; " );
    
    if ( has_ToString(obj) ) {
      System.out.print(ind_str);
      System.out.print( " value toString : ");
      System.out.print( obj.toString() );
      System.out.println( " ; " );
      return;
    }
    if ( obj.getClass().isEnum() ) {
      System.out.print(ind_str);
      System.out.print( " value ENUM : ");
      System.out.print( obj.toString() );
      System.out.println( " ; " );
      return;
    }

    Field[] fields = obj.getClass().getDeclaredFields();
    for ( Field field : fields  ) {
      System.out.print( ind_str );
      System.out.print( ">" );
      System.out.print( field.getName() );
      System.out.println( ": ");
      try {
        //requires access to private field:
        //System.out.print( field.get(obj) );
        recur_print_object_field( field.get(obj), indent+1 );
      } catch ( IllegalAccessException ex ) {
        System.out.print( " ERROR: ");
        System.out.print(ex);
        System.out.println(" ;");
      }
    }
  }
  
  public static void main(String[] args) throws FileNotFoundException, IOException, MalformedObjectNameException,  ClassNotFoundException  {
      System.out.println("Working on it ... "); 

      boolean show_val = false;

      for (String arg: args) {  
        if (arg.contains("show")) {
          show_val = true;
        }
    }

      ObjectInputStream ois;
      ois = new ObjectInputStream(new FileInputStream(args[0]));
      Object obj = ois.readObject();
      ois.close();

      System.out.println("Read object :");
      System.out.println(obj);

      if ( show_val ) { 
        System.out.println("----------\nObject Value(s):");
        recur_print_object_field(obj, 0);
      }
    }
}
