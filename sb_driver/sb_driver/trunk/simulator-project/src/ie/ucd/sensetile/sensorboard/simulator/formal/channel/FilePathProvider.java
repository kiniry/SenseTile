package ie.ucd.sensetile.sensorboard.simulator.formal.channel;

/**
 * Define A full absolute path(s) 
 * that points to the location(s) on sensor(s) file(s).
 * This class represents a singleton inastance helper.
 * @title         "FilePathProvider"
 * @date          "2009/08/07"
 * @author        "Dragan Stosic"
 * @organisation  "School of Computer Science and Informatics, UCD"
 * @copyright     "Copyright (C) 2009 UCD"
 * @version       "$ Revision: 1.00 $"
 */
public final class FilePathProvider {
	
	//@ spec_public non_null
	private static  String[] sens_pats;
	//@ spec_public non_null
	private static  String[] sound_pats;
	//@ spec_public non_null
	private static  String[] ultra_pats;
		
  	//@ invariant sens_pats.length == 6;

  	//@ invariant sound_pats.length == 4;
	
  	//@ invariant ultra_pats.length == 4;
	
  /*@ public normal_behavior
    @ requires thePaths.length == 6;
    @ requires theSoundPaths.length == 4;
    @ requires theUltraSoundPaths.length == 4;
    @ assignable  ultra_pats, ultra_pats[*];
    @ assignable  sound_pats,sound_pats[*];
    @ assignable  sens_pats, sens_pats[*];
    @*/ 
	public  FilePathProvider( /*@non_null@*/ final String[] thePaths,
							/*@non_null@*/ final String[] theSoundPaths, 
							/*@non_null@*/ final String[] theUltraSoundPaths   )
	{
		 sens_pats = new String[thePaths.length];
		 sound_pats= new String[theSoundPaths.length];
		 ultra_pats= new String[theUltraSoundPaths.length];
		 System.arraycopy(thePaths, 0, sens_pats, 0, thePaths.length);
		 System.arraycopy(theSoundPaths, 0, sound_pats, 0, theSoundPaths.length);
		 System.arraycopy(theUltraSoundPaths, 0, ultra_pats, 0, theUltraSoundPaths.length);
	}
	
	/*@ requires index >= 0 && index < 6;
	  @ ensures \result == null || \result instanceof String;
	  @*/
	public /*@pure*/ String getSensPath(final int index)
	{
		return sens_pats[index];
	}

	/*@ public normal_behavior
	  @ requires index >= 0 && index < 4;
	  @*/
	public /*@pure nullable*/ String getSoundPath(final int index)
	{
		return sound_pats[index];
	}
	/*@ public normal_behavior
	  @ requires index >= 0 && index < 4;
	  @*/
	public /*@pure nullable*/ String getUltraSoundPath(final int index)
	{
		return ultra_pats[index];
	}
}
