package logictechcorp.netherex.util;

public class NEEnumUtils
{
    public static <E extends Enum<E>> E fromOrdinal(Class<E> enumClass, int ordinal, boolean wrap)
    {
        E[] enumValues = enumClass.getEnumConstants();
        int length = enumValues.length;

        if (ordinal < 0)
        {
            ordinal = 0;
        }
        else if (ordinal >= length)
        {
            ordinal = wrap ? 0 : length - 1;
        }

        return enumValues[ordinal];
    }
}