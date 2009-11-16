package ie.ucd.sensetile.swe;

/**
 * Integer number used for a counting value.
 * ,Count,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class Count  {

    public Count(){}

  //@ assignable constraintSML, value;
  //@ requires constraintIn / =Void;
  //@ ensures value == valueIn;
  //@ ensures constraintSML == dataIn;
  public Count(/*@ non_null @*/ int valueIn, AllowedValuesPropertyType constraintIn){}

  public /*@ non_null @*/ AllowedValuesPropertyType constraintSML;
  public /*@ non_null @*/ int value;
}

