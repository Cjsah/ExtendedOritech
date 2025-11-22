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
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;
import rearth.oritech.Oritech;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;
import rearth.oritech.block.entity.addons.AddonBlockEntity;
import rearth.oritech.util.InventoryInputMode;
import rearth.oritech.util.MachineAddonController;
import rearth.oritech.util.ScreenProvider;

import java.util.List;

public class PluginAddonExtenderBlockEntity extends AddonBlockEntity implements MenuProvider, ScreenProvider {
    private final StackHandler itemStackHandler = new StackHandler(this);
    private MachineAddonBlock.AddonSettings settings = MachineAddonBlock.AddonSettings.getDefaultSettings()
        .withSpeedMultiplier(0)
        .withEfficiencyMultiplier(0);

    public PluginAddonExtenderBlockEntity(BlockEntityType<PluginAddonExtenderBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void setChanged() {
        super.setChanged();

        float speed = 0f;
        float efficiency = 0f;
        long energyAmount = 0L;
        long energyInsert = 0L;
        int extraChambers = 0;
        int extraBurstTicks = 0;

        ItemStack stack = this.itemStackHandler.getStackInSlot(0);
        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof MachineAddonBlock addonBlock) {
            MachineAddonBlock.AddonSettings addonSettings = addonBlock.getAddonSettings();
            int count = Math.min(stack.getCount(), 16);
            if (Oritech.CONFIG.additiveAddons()) {
                speed = (1 - addonSettings.speedMultiplier()) * count;
                efficiency = (1 - addonSettings.efficiencyMultiplier()) * count;
            } else {
                speed = (float) Math.pow(addonSettings.speedMultiplier(), count);
                efficiency = (float) Math.pow(addonSettings.efficiencyMultiplier(), count);
            }
            energyAmount = addonSettings.addedCapacity() * count;
            energyInsert = addonSettings.addedInsert() * count;
            extraChambers = addonSettings.chamberCount() * count;
            extraBurstTicks = addonSettings.burstTicks() * count;
        }

        this.settings = new MachineAddonBlock.AddonSettings(
            false,
            speed, efficiency, energyAmount, energyInsert,
            false, true,
            extraChambers, extraBurstTicks, null);

        if (this.level == null || this.level.isClientSide()) return;
        BlockPos pos = this.getControllerPos();
        if (!this.level.isLoaded(pos)) return;
        if (this.level.getBlockEntity(pos) instanceof MachineAddonController controller) {
            controller.initAddons();
        }
    }

    public MachineAddonBlock.AddonSettings getSettings() {
        return this.settings;
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
        return new PluginAddonExtenderMenu(i, inventory, this);
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

    @Override
    public MenuType<?> getScreenHandlerType() {
        return ModMenuTypes.PLUGIN_ADDON_EXTENDER.get();
    }

    @Override
    public Container getDisplayedInventory() {
        return this.itemStackHandler;
    }

    @Override
    public List<GuiSlot> getGuiSlots() {
        return List.of(new GuiSlot(0, 80, 35));
    }

    @Override
    public InventoryInputMode getInventoryInputMode() {
        return InventoryInputMode.FILL_LEFT_TO_RIGHT;
    }

    @Override
    public boolean showEnergy() {
        return false;
    }

    @Override
    public boolean showProgress() {
        return false;
    }

    @Override
    public float getDisplayedEnergyUsage() {
        return 0;
    }

    @Override
    public float getProgress() {
        return 0;
    }

}
