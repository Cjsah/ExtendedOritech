package net.cjsah.mod.extendedoritech.init;

import net.cjsah.mod.extendedoritech.ExtendedOritech;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = ExtendedOritech.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
            Capabilities.ItemHandler.BLOCK,
            ModBlockEntities.PLUGIN_ADDON_EXTENDER.get(),
            (be, side) -> be.getItemHandler());
    }


}
