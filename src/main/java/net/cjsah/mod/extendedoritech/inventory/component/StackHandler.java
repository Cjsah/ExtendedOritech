package net.cjsah.mod.extendedoritech.inventory.component;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;

public class StackHandler extends ItemStackHandler {
    private final PluginAddonExtenderBlockEntity blockEntity;

    public StackHandler(PluginAddonExtenderBlockEntity blockEntity) {
        this.blockEntity = blockEntity;
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
