package net.cjsah.mod.extendedoritech.mixin;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import rearth.oritech.Oritech;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;
import rearth.oritech.block.entity.addons.CombiAddonEntity;
import rearth.oritech.util.MachineAddonController;

import java.util.List;

@Mixin(MachineAddonController.class)
public interface MachineAddonControllerMixin {

    @Shadow
    void getAdditionalStatFromAddon(MachineAddonController.AddonBlock addonBlock);
    @Shadow
    void setBaseAddonData(MachineAddonController.BaseAddonData data);

    /**
     * @author Cjsah
     * @reason mixin not support inject default method in interface
     */
    @Overwrite
    default void gatherAddonStats(List<MachineAddonController.AddonBlock> addons) {

        if (addons.size() == 1 && addons.getFirst().addonEntity() instanceof CombiAddonEntity combiAddonEntity) {
            getAdditionalStatFromAddon(addons.getFirst());
            setBaseAddonData(combiAddonEntity.getBaseData());
            return;
        }

        var speed = 1f;
        var efficiency = 1f;
        var energyAmount = 0L;
        var energyInsert = 0L;
        var extraChambers = 0;
        var extraBurstTicks = 0;

        for (var addon : addons) {
            var addonSettings = addon.addonBlock().getAddonSettings();

            if (Oritech.CONFIG.additiveAddons()) {
                speed += 1 - addonSettings.speedMultiplier();
                efficiency += 1 - addonSettings.efficiencyMultiplier();
            } else {
                speed *= addonSettings.speedMultiplier();
                efficiency *= addonSettings.efficiencyMultiplier();
            }

            energyAmount += addonSettings.addedCapacity();
            energyInsert += addonSettings.addedInsert();
            extraChambers += addonSettings.chamberCount();
            extraBurstTicks += addonSettings.burstTicks();

            if (addon.addonEntity() instanceof PluginAddonExtenderBlockEntity be) {
                MachineAddonBlock.AddonSettings settings = be.getSettings();

                if (Oritech.CONFIG.additiveAddons()) {
                    speed += 1 - settings.speedMultiplier();
                    efficiency += 1 - settings.efficiencyMultiplier();
                } else {
                    speed *= settings.speedMultiplier();
                    efficiency *= settings.efficiencyMultiplier();
                }

                energyAmount += settings.addedCapacity();
                energyInsert += settings.addedInsert();
                extraChambers += settings.chamberCount();
                extraBurstTicks += settings.burstTicks();
            }

            getAdditionalStatFromAddon(addon);
        }

        if (Oritech.CONFIG.additiveAddons()) {

            speed = 1f / speed;

            var efficiencyChange = efficiency - 1;
            efficiency = 1f / efficiency;
            if (efficiencyChange < 0) {
                efficiency = 1 + Math.abs(efficiencyChange);   // yes this order looks stupid, but it's easier to understand like this for me
            }
        }

        var baseData = new MachineAddonController.BaseAddonData(speed, efficiency, energyAmount, energyInsert, extraChambers, extraBurstTicks);
        setBaseAddonData(baseData);

    }
}
