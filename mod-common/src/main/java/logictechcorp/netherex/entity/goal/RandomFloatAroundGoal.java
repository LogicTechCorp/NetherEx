package logictechcorp.netherex.entity.goal;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class RandomFloatAroundGoal extends Goal
{
    private final Mob mob;

    public RandomFloatAroundGoal(Mob inMob)
    {
        mob = inMob;
        setFlags(EnumSet.of(Flag.MOVE));
    }

    public void start()
    {
        RandomSource random = mob.getRandom();
        double posX = mob.getX() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
        double posY = mob.getY() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
        double posZ = mob.getZ() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
        mob.getMoveControl().setWantedPosition(posX, posY, posZ, 1.0d);
    }

    public boolean canUse()
    {
        MoveControl movecontrol = mob.getMoveControl();

        if (!movecontrol.hasWanted())
        {
            return true;
        }
        else
        {
            double d0 = movecontrol.getWantedX() - mob.getX();
            double d1 = movecontrol.getWantedY() - mob.getY();
            double d2 = movecontrol.getWantedZ() - mob.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < (double) 1.0F || d3 > (double) 3600.0F;
        }
    }

    public boolean canContinueToUse()
    {
        return false;
    }
}
