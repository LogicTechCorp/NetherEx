package logictechcorp.netherex.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum NEFumaroleStage implements StringRepresentable
{
    HEAT_UP,
    ERUPTION,
    COOL_DOWN;

    @Override
    public String getSerializedName()
    {
        return name().toLowerCase();
    }
}
