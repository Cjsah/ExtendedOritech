package net.cjsah.mod.extendedoritech.inventory;

import net.cjsah.mod.extendedoritech.ExtendedOritech;
import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class PluginAddonExtenderMenu extends AbstractContainerMenu implements ContainerListener {
    private final PluginAddonExtenderBlockEntity blockEntity;
    private final Level level;
    private final Inventory inventory;
//    private final ContainerData sync;

    public PluginAddonExtenderMenu(MenuType<?> menuType, int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(menuType, containerId, inventory, readBlockEntity(extraData, inventory));
    }

    public PluginAddonExtenderMenu(MenuType<?> menuType, int containerId, Inventory inventory, PluginAddonExtenderBlockEntity blockEntity) {
        super(menuType, containerId);
//        checkContainerDataCount(sync, 4);
        this.blockEntity = blockEntity;
        this.inventory = inventory;
        this.level = inventory.player.level();
//        this.sync = sync;
        this.addContainerInventory(blockEntity);
        this.addPlayerInventory(inventory);
        this.addSlotListener(this);
//        this.addDataSlots(sync);
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }

    protected void addContainerInventory(PluginAddonExtenderBlockEntity blockEntity) {
        IItemHandler itemHandler = blockEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 80, 35));

    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack slotItem = slot.getItem();
        ItemStack backupItem = slotItem.copy();
        if (!this.quickMoveStack(index, slotItem)) {
            return ItemStack.EMPTY;
        }

        if (slotItem.getCount() == 0) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (slotItem.getCount() == backupItem.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, backupItem);
        return backupItem;
    }

    private boolean quickMoveStack(int index, ItemStack stack) {
        if (index == 0) {
            return this.moveItemStackTo(stack, 1, 37, true);
        } else if (index < 37) {
            if (this.moveItemStackTo(stack, 0, 1, false)) return true;
            if (index < 28) {
                return this.moveItemStackTo(stack, 28, 37, false);
            } else {
                return this.moveItemStackTo(stack, 1, 28, false);
            }
        } else {
            ExtendedOritech.LOGGER.warn("Invalid slot {} on Plugin Addon Extender.", index);
            return false;
        }
    }


    @Override
    public boolean stillValid(Player player) {
        return ContainerLevelAccess.create(this.level, this.blockEntity.getBlockPos())
            .evaluate((level, pos) -> player.canInteractWithBlock(pos, 4.0F), true);
    }

    protected static PluginAddonExtenderBlockEntity readBlockEntity(FriendlyByteBuf buffer, Inventory inventory) {
        //noinspection resource
        return (PluginAddonExtenderBlockEntity) inventory.player.level().getBlockEntity(buffer.readBlockPos());
    }

    @Override
    public void slotChanged(AbstractContainerMenu menu, int i, ItemStack itemStack) {
    }

    @Override
    public void dataChanged(AbstractContainerMenu menu, int i, int i1) {
    }
}
