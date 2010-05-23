/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.utils;

/**
 *
 * @author SenseTile
 */
public class CommonUtils
{
    public static <Type> boolean isTypeOf( final Type message, final String clazzName )
    {
        assert message != null: "Message cannot be a null.";
        assert clazzName != null  && !clazzName.equalsIgnoreCase(""):
            "Source cannot be a null.or empty string.";
         boolean isInstanceOf = Boolean.FALSE;
         try {
               Class aClass = Class.forName(clazzName);
                if(aClass.isInstance(message))
                {
                   isInstanceOf = Boolean.TRUE;
                }
            } catch (ClassNotFoundException ex) {
                throw new UnsupportedOperationException("A class name should be a valid.", ex);
            }
         return isInstanceOf;
    }

}
