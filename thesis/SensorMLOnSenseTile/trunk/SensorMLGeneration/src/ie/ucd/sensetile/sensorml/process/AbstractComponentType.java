package ie.ucd.sensetile.sensorml.process;

/**
 * Complex Type for all generic components (soft typed inputs/outputs/parameters).
 * ,AbstractComponentType,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class AbstractComponentType extends AbstractDerivableComponentType {

  //@ assignable inputs, outputs;
  //@ requires outputsIn / =Void;
  //@ ensures inputs=inputsIn;
  //@ ensures outputs=outputsIn;
  //public AbstractComponentType(/*@ non_null @*/ Inputs inputsIn, Outputs outputsIn){}
  public AbstractComponentType(){}
  public AbstractComponentType(/*@ non_null @*/ Inputs inputsIn){}

  public /*@ non_null @*/ Inputs inputs;
  //public /*@ non_null @*/ Outputs outputs;
  //public Parameters parameters;
}
