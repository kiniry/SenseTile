package ie.ucd.sensetile.sensorml.process;

import ie.ucd.sensetile.swe.Count;

/**
 * IoComponentPropertyType.
 * ,IoComponentPropertyType,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class IoComponentPropertyType  {

  //@ assignable data, nameAttrib;
  //@ requires dataIn / =Void;
  //@ ensures nameAtrib == nameIn;
  //@ ensures data == dataIn;
  public IoComponentPropertyType(/*@ non_null @*/ String nameIn, Count count){
      this.count = count;
      this.nameAtrib = nameIn;
  }

  public /*@ non_null @*/ Count count;
  public /*@ non_null @*/ String nameAtrib;
}

