package logictechcorp.netherex.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum NENetherrackType implements StringRepresentable
{
    GLOOMY("gloomy"),
    FIERY("fiery"),
    LIVELY("lively");

    private final String name;

    NENetherrackType(String inName)
    {
        name = inName;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public String getSerializedName()
    {
        return name;
    }
}
