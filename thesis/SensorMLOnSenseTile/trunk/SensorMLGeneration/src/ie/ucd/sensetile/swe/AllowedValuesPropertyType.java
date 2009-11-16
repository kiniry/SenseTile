package ie.ucd.sensetile.swe;

/**
 * AllowedValuesPropertyType.
 * ,AllowedValuesPropertyType,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class AllowedValuesPropertyType  {

  //@ assignable allowedValues;
  //@ ensures allowedValues == AllowedValuesIn;
  public AllowedValuesPropertyType(/*@ non_null @*/ AllowedValues allowedValuesIn){}

  public /*@ non_null @*/ AllowedValues allowedValues;
}
