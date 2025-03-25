package logictechcorp.netherex.block.entity;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NEKilnBlockEntity extends AbstractFurnaceBlockEntity
{
    public NEKilnBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(NetherExBlockEntityTypes.KILN.get(), pos, blockState, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("container." + NetherExConstants.MOD_ID + ".kiln");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory)
    {
        return new FurnaceMenu(containerId, playerInventory, this, dataAccess);
    }
}
