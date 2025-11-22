package net.cjsah.mod.extendedoritech.client.screen;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.Color;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.Sizing;
import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.cjsah.mod.extendedoritech.inventory.PluginAddonExtenderMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;
import rearth.oritech.client.ui.BasicMachineScreen;

public class PluginAddonExtenderScreen extends BasicMachineScreen<PluginAddonExtenderMenu> {

    private LabelComponent speedLabel;
    private LabelComponent efficiencyLabel;
    private LabelComponent chamberLabel;

    public PluginAddonExtenderScreen(PluginAddonExtenderMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
        super.slotClicked(slot, slotId, mouseButton, type);
        this.updateLabels();
    }

    private void updateLabels() {
        if (this.speedLabel == null || this.efficiencyLabel == null || this.chamberLabel == null) return;
        if (!(this.menu.blockEntity instanceof PluginAddonExtenderBlockEntity blockEntity)) return;
        MachineAddonBlock.AddonSettings settings = blockEntity.getSettings();

        int speed = Math.round(settings.speedMultiplier() * 20) * 5;
        int efficiency = Math.round(settings.efficiencyMultiplier() * 20) * 5;
        int extraChambers = settings.chamberCount();

        String efficiencyText = (efficiency > 0 ? "+" : "") + efficiency;

        this.speedLabel.text(Component.translatable("title.oritech.machine_speed", speed));
        this.efficiencyLabel.text(Component.translatable("title.oritech.machine_efficiency", efficiencyText));
        this.chamberLabel.text(Component.translatable("title.oritech.chambers", extraChambers));
    }

    @Override
    public void addExtensionComponents(FlowLayout container) {
        super.addExtensionComponents(container);

        this.speedLabel = Components.label(Component.translatable("title.oritech.machine_speed", "?"));
        this.efficiencyLabel = Components.label(Component.translatable("title.oritech.machine_efficiency", "?"));
        this.chamberLabel = Components.label(Component.translatable("title.oritech.chambers", "?"));

        this.updateLabels();

        container.child(Components.box(Sizing.fixed(73), Sizing.fixed(1)).color(new Color(0.8f, 0.8f, 0.8f)));
        container.child(this.speedLabel.tooltip(Component.translatable("tooltip.oritech.machine_speed")).margins(Insets.of(3)));
        container.child(this.efficiencyLabel.tooltip(Component.translatable("tooltip.oritech.machine_efficiency")).margins(Insets.of(3)));
        container.child(this.chamberLabel.tooltip(Component.translatable("tooltip.oritech.chambers")).margins(Insets.of(3)));
    }

}
