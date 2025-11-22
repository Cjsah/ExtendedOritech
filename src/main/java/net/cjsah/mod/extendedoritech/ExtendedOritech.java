package net.cjsah.mod.extendedoritech;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;
import net.cjsah.mod.extendedoritech.datagen.ModLangProvider;
import net.cjsah.mod.extendedoritech.init.ModBlockEntities;
import net.cjsah.mod.extendedoritech.init.ModBlocks;
import net.cjsah.mod.extendedoritech.init.ModCreativeTabs;
import net.cjsah.mod.extendedoritech.init.ModMenuTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ExtendedOritech.MODID)
public class ExtendedOritech {

    public static final String MODID = "extendedoritech";
    public static final String MODNAME = "Extended Oritech";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Registrate REGISTRATE = Registrate.create(MODID).defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public ExtendedOritech(IEventBus bus, ModContainer container, Dist dist) {
        ModCreativeTabs.register(bus);
        ModBlocks.init();
        ModBlockEntities.init();
        ModMenuTypes.init();

        REGISTRATE.addDataGenerator(ProviderType.LANG, ModLangProvider::register);
    }

}
