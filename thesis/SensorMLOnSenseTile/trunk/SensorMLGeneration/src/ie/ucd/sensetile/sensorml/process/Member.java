package ie.ucd.sensetile.sensorml.process;

/**
 * SensorML member.
 * ,connections,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class Member  {

  
  public Member(){}

  //@ assignable arcrole, process;
  //@ requires arcroleIn / =Void;
  //@ ensures process == processIn;
  //@ ensures arcrole == arcroleIn;
  public Member(String arcroleIn, /*@ non_null @*/ AbstractProcessType processIn){
      this.arcrole = arcroleIn;
      this.process = processIn;
  }

  public /*@ non_null @*/ String arcrole;
  public /*@ non_null @*/ AbstractProcessType process;
}
