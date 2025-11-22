package net.cjsah.mod.extendedoritech.inventory;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import rearth.oritech.client.ui.BasicMachineScreenHandler;

public class PluginAddonExtenderMenu extends BasicMachineScreenHandler {

    public PluginAddonExtenderMenu(int containerId, Inventory inventory, FriendlyByteBuf buffer) {
        super(containerId, inventory, buffer);
    }

    public PluginAddonExtenderMenu(int containerId, Inventory inventory, PluginAddonExtenderBlockEntity blockEntity) {
        super(containerId, inventory, blockEntity);
    }

}
