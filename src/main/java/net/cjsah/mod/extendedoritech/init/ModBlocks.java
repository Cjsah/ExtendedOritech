package net.cjsah.mod.extendedoritech.init;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.cjsah.mod.extendedoritech.ExtendedOritech;
import net.cjsah.mod.extendedoritech.block.PluginAddonExtenderBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import rearth.oritech.block.blocks.addons.MachineAddonBlock;
import rearth.oritech.block.blocks.processing.MachineCoreBlock;

import static net.cjsah.mod.extendedoritech.ExtendedOritech.REGISTRATE;

public class ModBlocks {

    public static final BlockEntry<? extends Block> MACHINE_CORE_INF = REGISTRATE
        // because float precision loss, quality set to 88888
        .block("machine_core_inf", p -> new MachineCoreBlock(p, 88888))
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .lang("Creative Machine Core")
        .simpleItem()
        .register();

    public static final BlockEntry<? extends Block> PLUGIN_ADDON_EXTENDER = REGISTRATE
        .block("plugin_addon_extender", PluginAddonExtenderBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .blockstate((ctx, prov) -> {
            String name = ctx.getName();
            ModelFile activeModel = prov.models().cubeAll(name, ExtendedOritech.of("block/" + name));
            ModelFile noActiveModel = prov.models().cubeAll(name + "_disabled", ExtendedOritech.of("block/" + name + "_disabled"));
            prov.getVariantBuilder(ctx.get())
                .partialState().with(MachineAddonBlock.ADDON_USED, true)
                .addModels(new ConfiguredModel(activeModel))
                .partialState().with(MachineAddonBlock.ADDON_USED, false)
                .addModels(new ConfiguredModel(noActiveModel));
        })
        .simpleItem()
        .register();

    public static void init() {
    }

}
