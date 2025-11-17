package net.cjsah.mod.extendedoritech.block;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;

public class PluginAddonExtenderBlock extends MachineAddonBlock {
    public PluginAddonExtenderBlock(Properties settings) {
        super(settings, AddonSettings.getDefaultSettings().withExtender(true).withNeedsSupport(false));
    }

    @Override
    public @NotNull Class<? extends BlockEntity> getBlockEntityType() {
        return PluginAddonExtenderBlockEntity.class;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PluginAddonExtenderBlockEntity(pos, state);
    }

}
