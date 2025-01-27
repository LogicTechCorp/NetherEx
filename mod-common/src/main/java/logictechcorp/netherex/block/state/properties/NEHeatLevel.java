package logictechcorp.netherex.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum NEHeatLevel implements StringRepresentable
{
    NO_HEAT(30, 15, 60, 2, 4),
    LOW_HEAT(20, 10, 40, 5, 7),
    HIGH_HEAT(10, 5, 20, 8, 10);

    final private int heatUpTime;
    final private int eruptionTime;
    final private int coolDownTime;
    final private int minHeight;
    final private int maxHeight;

    NEHeatLevel(int inHeatUpTime, int inEruptionTime, int inCoolDownTime, int inMinHeight, int inMaxHeight)
    {
        heatUpTime = inHeatUpTime;
        eruptionTime = inEruptionTime;
        coolDownTime = inCoolDownTime;
        minHeight = inMinHeight;
        maxHeight = inMaxHeight;
    }

    public int getHeatUpTime()
    {
        return heatUpTime;
    }

    public int getEruptionTime()
    {
        return eruptionTime;
    }

    public int getCoolDownTime()
    {
        return coolDownTime;
    }

    public int getMinHeight()
    {
        return minHeight;
    }

    public int getMaxHeight()
    {
        return maxHeight;
    }

    @Override
    public String getSerializedName()
    {
        return name().toLowerCase();
    }
}
