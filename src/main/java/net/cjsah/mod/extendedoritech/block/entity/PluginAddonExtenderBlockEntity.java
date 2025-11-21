package net.cjsah.mod.extendedoritech.block.entity;

import net.cjsah.mod.extendedoritech.init.ModBlockEntities;
import net.cjsah.mod.extendedoritech.init.ModBlocks;
import net.cjsah.mod.extendedoritech.init.ModMenuTypes;
import net.cjsah.mod.extendedoritech.inventory.PluginAddonExtenderMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;
import rearth.oritech.block.entity.addons.AddonBlockEntity;

public class PluginAddonExtenderBlockEntity extends AddonBlockEntity implements MenuProvider {
    public PluginAddonExtenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLUGIN_ADDON_EXTENDER.get(), pos, state);
    }

    public PluginAddonExtenderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public MachineAddonBlock.AddonSettings getSettings() {
        return MachineAddonBlock.AddonSettings.getDefaultSettings().withExtender(true).withNeedsSupport(false);
    }

    @Override
    public Component getDisplayName() {
        return ModBlocks.PLUGIN_ADDON_EXTENDER.get().getName();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        if (player.isSpectator()) return null;
        return new PluginAddonExtenderMenu(ModMenuTypes.PLUGIN_ADDON_EXTENDER.get(), i, inventory, this);
    }
}
