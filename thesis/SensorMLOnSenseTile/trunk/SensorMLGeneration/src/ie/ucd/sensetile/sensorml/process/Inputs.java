package ie.ucd.sensetile.sensorml.process;

import java.util.List;

/**
 * Inputs.
 * ,Inputs,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class Inputs  {

  //@ assignable inputListElem;
  //@ ensures inputList == inputListIn;
  public Inputs(/*@ non_null @*/ List<IoComponentPropertyType> inputListIn){}

  public /*@ non_null @*/ List<IoComponentPropertyType> inputList;
}
