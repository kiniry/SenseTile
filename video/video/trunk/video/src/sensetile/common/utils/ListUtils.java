
package sensetile.common.utils;

import java.util.List;

public class ListUtils
{

	/**
	 * Performs type casting and adds all objects to a supplied list.
	 * @param <Type> A list' generic type.
	 * @param targetList A list to add objects to.
	 * @param objects Actual (erased) objects.
	 */
	public static <Type> void addToTypedList( List<Type> targetList, Object[] objects )
        {
           Guard.ArgumentNotNull(targetList, "Target list cannot be a null");
           Guard.ArgumentNotNull(objects, "Source objects cannot be a null");

            for( Object anObject : objects )
            {
                    Type typedObject = (Type) anObject;
                    targetList.add( typedObject );
            }
	}

        public static <Type> boolean listIsAssignableFrom( final List<Type> targetList,
            final String clazzName )
        {
           Guard.ArgumentNotNull(targetList, "Target list cannot be a null");
           Guard.ArgumentNotNullOrEmptyString(clazzName, "Source objects cannot be a null");
           boolean isTypeOf = Boolean.FALSE;
            if(!targetList.isEmpty())
            {
                Type first = targetList.get(0);
                isTypeOf =CommonUtils.isTypeOf(first,clazzName);

            }
            return isTypeOf;
        }

    
}
