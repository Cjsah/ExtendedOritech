package net.cjsah.mod.extendedoritech.inventory.component;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;

public class StackHandler extends ItemStackHandler implements Container {
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
        return this.getMaxStackSize();
    }

    @Override
    public int getMaxStackSize() {
        return 16;
    }

    @Override
    protected void onContentsChanged(int slot) {
        this.setChanged();
    }

    @Override
    public int getContainerSize() {
        return this.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return this.getStackInSlot(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return this.extractItem(slot, count, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stack = this.getStackInSlot(slot);
        this.setStackInSlot(slot, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.setStackInSlot(slot, itemStack);
    }

    @Override
    public void setChanged() {
        this.blockEntity.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this.blockEntity, player);
    }

    @Override
    public void clearContent() {
        this.stacks.clear();
        this.setChanged();
    }
}
