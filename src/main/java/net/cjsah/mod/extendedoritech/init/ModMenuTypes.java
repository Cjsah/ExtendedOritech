package net.cjsah.mod.extendedoritech.init;

import com.tterrag.registrate.util.entry.MenuEntry;
import net.cjsah.mod.extendedoritech.client.screen.PluginAddonExtenderScreen;
import net.cjsah.mod.extendedoritech.inventory.PluginAddonExtenderMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;

import static net.cjsah.mod.extendedoritech.ExtendedOritech.REGISTRATE;

public class ModMenuTypes {

    public static final MenuEntry<PluginAddonExtenderMenu> PLUGIN_ADDON_EXTENDER = REGISTRATE
        .menu("crafting_machine", PluginAddonExtenderMenu::new, () -> PluginAddonExtenderScreen::new)
        .register();

    public static void open(ServerPlayer player, MenuProvider menu, BlockPos pos) {
        player.openMenu(menu, pos);
    }

    public static void init() {
    }
}
