package net.cjsah.mod.extendedoritech.block;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.cjsah.mod.extendedoritech.init.ModBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;

import java.util.List;

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
        return new PluginAddonExtenderBlockEntity(ModBlockEntities.PLUGIN_ADDON_EXTENDER.get(), pos, state);
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return super.getMenuProvider(state, level, pos);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (player.isSpectator()) return InteractionResult.PASS;
        if (level.isClientSide) return InteractionResult.SUCCESS;
        if (level.getBlockEntity(pos) instanceof PluginAddonExtenderBlockEntity blockEntity) {
            player.openMenu(blockEntity, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag options) {
        super.appendHoverText(stack, context, tooltip, options);

        if (Screen.hasControlDown()) {
            tooltip.add(Component.translatable("tooltip.extendedoritech.plugin_addon_extender").withStyle(ChatFormatting.GRAY));
        }

    }
}
