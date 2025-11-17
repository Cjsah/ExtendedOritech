package net.cjsah.mod.extendedoritech.block.entity;

import net.cjsah.mod.extendedoritech.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import rearth.oritech.block.entity.addons.AddonBlockEntity;

public class PluginAddonExtenderBlockEntity extends AddonBlockEntity {
    public PluginAddonExtenderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLUGIN_ADDON_EXTENDER.get(), pos, state);
    }

    public PluginAddonExtenderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
