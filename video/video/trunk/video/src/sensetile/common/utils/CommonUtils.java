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
        Guard.ArgumentNotNull(message, "Target list cannot be a null");
        Guard.ArgumentNotNullOrEmptyString(clazzName, "Source objects cannot be a null");
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
