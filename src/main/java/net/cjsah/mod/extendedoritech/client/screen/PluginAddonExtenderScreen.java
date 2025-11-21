package net.cjsah.mod.extendedoritech.client.screen;

import net.cjsah.mod.extendedoritech.inventory.PluginAddonExtenderMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class PluginAddonExtenderScreen extends AbstractContainerScreen<PluginAddonExtenderMenu> {
    public PluginAddonExtenderScreen(PluginAddonExtenderMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

    }
}
