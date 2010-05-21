package sensetile.common.utils;

import java.util.List;

    public  class Guard
    {
        public static void ArgumentNotNullOrEmptyString(String argumentValue, String description)
        {
            ArgumentNotNull(argumentValue, description);
            assert argumentValue.length() != 0: description;
        }
        public static void ArgumentNotNull(Object argumentValue, String description)
        {
             assert argumentValue != null:description;
        }
        public static void TypeIsAssignableFromType(Class<?> assignee,
                Object providedType, String description)
        {
                ArgumentNotNull(assignee, "assignee cannot be a null.");
                ArgumentNotNull(providedType, "providedType cannot be a null.");
                ArgumentNotNullOrEmptyString(description, "description cannot be a null or empty string.");
                assert !providedType.getClass().isAssignableFrom(assignee) : description;
                
        }

        public static void IndexOutOfBounds( List<?> targetList, int index )
        {
            ArgumentNotNull(targetList, "targetList cannot be a null.");
            assert index >=0 : "Index cannot be a less than 0";
            assert index < targetList.size() : "Index cannot be a greater than list size.";
        }
    }

