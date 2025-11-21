package net.cjsah.mod.extendedoritech.block.entity;

import net.cjsah.mod.extendedoritech.init.ModBlockEntities;
import net.cjsah.mod.extendedoritech.init.ModBlocks;
import net.cjsah.mod.extendedoritech.init.ModMenuTypes;
import net.cjsah.mod.extendedoritech.inventory.PluginAddonExtenderMenu;
import net.cjsah.mod.extendedoritech.inventory.component.StackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;
import rearth.oritech.block.entity.addons.AddonBlockEntity;

public class PluginAddonExtenderBlockEntity extends AddonBlockEntity implements MenuProvider {
    private final StackHandler itemStackHandler = new StackHandler(this);

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

    public IItemHandler getItemHandler() {
        return this.itemStackHandler;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        if (player.isSpectator()) return null;
        return new PluginAddonExtenderMenu(ModMenuTypes.PLUGIN_ADDON_EXTENDER.get(), i, inventory, this);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        ItemStack stack = ItemStack.EMPTY;
        if (tag.contains("Item", Tag.TAG_COMPOUND)) {
            stack = ItemStack.parse(provider, tag.getCompound("Item")).orElse(ItemStack.EMPTY);
        }
        this.itemStackHandler.setStackInSlot(0, stack);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        ItemStack stack = this.itemStackHandler.getStackInSlot(0);
        if (stack.isEmpty()) return;
        tag.put("Item", stack.save(provider, new CompoundTag()));
    }


}
