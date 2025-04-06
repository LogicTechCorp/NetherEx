package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class NetherExBlockTags
{
    public static final TagKey<Block> NETHERRACK = createTag("netherrack");
    public static final TagKey<Block> THORNSTALK_PLACEABLE_ON = createTag("thornstalk_placeable_on");
    public static final TagKey<Block> OBSIDIAN_HEATER = createTag("obsidian_heater");
    public static final TagKey<Block> OBSIDIAN_COOLER = createTag("obsidian_cooler");
    public static final TagKey<Block> LOW_FUMAROLE_HEATER = createTag("low_fumarole_heater");
    public static final TagKey<Block> HIGH_FUMAROLE_HEATER = createTag("high_fumarole_heater");

    public static TagKey<Block> createTag(String name)
    {
        return TagKey.create(Registries.BLOCK, NetherExConstants.resource(name));
    }
}