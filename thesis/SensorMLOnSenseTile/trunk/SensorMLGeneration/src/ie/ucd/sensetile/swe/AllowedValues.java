package ie.ucd.sensetile.swe;

import java.util.List;

/**
 * AllowedValues.
 * ,AllowedValues,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class AllowedValues  {

  public AllowedValues(){}

  //@ assignable interval;
  //@ requires intervalIn . length == 2;
  //@ ensures interval == intervalIn;
  public AllowedValues(/*@ non_null @*/ List<Double> intervalIn){}

  public List<Double> interval;
  public Double max;
  public Double min;
  public List<Double> valueList;
}
