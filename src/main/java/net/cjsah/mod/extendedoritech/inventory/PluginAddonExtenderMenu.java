package net.cjsah.mod.extendedoritech.inventory;

import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PluginAddonExtenderMenu extends AbstractContainerMenu {
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
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
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

}
