package ie.ucd.sensetile.sensorml.process;

import java.util.List;

/**
 * SensorML document root.
 * ,connections,none,School of Computer Science and Informatics, UCD,2009/10/29
 * @author Ciaran Palmer.
 * @version Revision: 1.00.
 */
public /*@ nullable_by_default @*/ class SensorML  {

  public /*@ non_null @*/ Member member;
  
  public SensorML(){}

  //@ ensures (* for_all i:INTEGER such_that 0 <= i *);
  //@ ensures (* i < member_array . length it_holds member . item(i) == member_array . item(i) *);
  public SensorML(final/*@ non_null @*/ Member memberarray) {
      //for (int i = 0; i < memberarray.length; i++) {
          //member[i] = memberarray[i];
      //}
      this.member = memberarray;
  }


}
