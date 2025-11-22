package net.cjsah.mod.extendedoritech.init;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.cjsah.mod.extendedoritech.block.entity.PluginAddonExtenderBlockEntity;

import static net.cjsah.mod.extendedoritech.ExtendedOritech.REGISTRATE;

public class ModBlockEntities {
    public static final BlockEntityEntry<PluginAddonExtenderBlockEntity> PLUGIN_ADDON_EXTENDER = REGISTRATE
        .blockEntity("plugin_addon_extender", PluginAddonExtenderBlockEntity::new)
        .validBlock(ModBlocks.PLUGIN_ADDON_EXTENDER)
        .register();

    public static void init() {
    }

}
