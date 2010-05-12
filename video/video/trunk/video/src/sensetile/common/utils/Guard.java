

package sensetile.common.utils;

import java.security.InvalidParameterException;
import java.util.List;

    public  class Guard
    {
        public static void ArgumentNotNullOrEmptyString(String argumentValue, String argumentName)
        {
            ArgumentNotNull(argumentValue, argumentName);
            if (argumentValue.length() == 0)
            {
              throw new InvalidParameterException(argumentName);
            }
        }
        public static void ArgumentNotNull(Object argumentValue, String argumentName)
        {
                if (argumentValue == null)
                {
                  throw new NullPointerException(argumentName);
                }
        }
        public static void TypeIsAssignableFromType(Class<?> assignee,
                Object providedType, String argumentName)
        {
                ArgumentNotNull(assignee, "assignee");
                ArgumentNotNull(providedType, "providedType");
                ArgumentNotNullOrEmptyString(argumentName, "argumentName");
                if (!providedType.getClass().isAssignableFrom(assignee))
                {
                     throw new InvalidParameterException(argumentName);
                }
        }

        public static void IndexOutOfBounds( List<?> targetList, int index )
        {
            ArgumentNotNull(targetList, "targetList");
            
            if(index <0 && index >= targetList.size())
            {
                throw new IndexOutOfBoundsException("targetList");
            }
        }
    }

