package net.cjsah.mod.extendedoritech.init;

import net.cjsah.mod.extendedoritech.ExtendedOritech;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.cjsah.mod.extendedoritech.ExtendedOritech.REGISTRATE;

@SuppressWarnings("unused")
public class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExtendedOritech.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = REGISTRY.register("tab", () -> CreativeModeTab.builder()
        .icon(() -> Blocks.IRON_BLOCK.asItem().getDefaultInstance())
        .displayItems((ctx, entries) -> {})
        .title(REGISTRATE.addLang("itemGroup", ExtendedOritech.of("tab"), ExtendedOritech.MODNAME))
        .build());

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
        REGISTRATE.defaultCreativeTab(ModCreativeTabs.TAB.getKey());
    }

}
