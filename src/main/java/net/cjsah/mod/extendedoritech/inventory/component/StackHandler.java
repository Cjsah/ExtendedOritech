package net.cjsah.mod.extendedoritech.inventory.component;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;

public class StackHandler extends ItemStackHandler {
    private final PluginAddonExtenderBlockEntity blockEntity;

    public StackHandler(PluginAddonExtenderBlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof MachineAddonBlock;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 16;
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.blockEntity.setChanged();
    }
}
