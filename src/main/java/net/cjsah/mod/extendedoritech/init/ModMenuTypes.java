package net.cjsah.mod.extendedoritech.init;

import com.tterrag.registrate.util.entry.MenuEntry;
import net.cjsah.mod.extendedoritech.client.screen.PluginAddonExtenderScreen;
import net.cjsah.mod.extendedoritech.inventory.PluginAddonExtenderMenu;

import static net.cjsah.mod.extendedoritech.ExtendedOritech.REGISTRATE;

public class ModMenuTypes {

    public static final MenuEntry<PluginAddonExtenderMenu> PLUGIN_ADDON_EXTENDER = REGISTRATE
        .menu("plugin_addon_extender", PluginAddonExtenderMenu::new, () -> PluginAddonExtenderScreen::new)
        .register();

    public static void init() {
    }
}
