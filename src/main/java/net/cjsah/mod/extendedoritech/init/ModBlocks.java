package net.cjsah.mod.extendedoritech.init;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import rearth.oritech.block.blocks.processing.MachineCoreBlock;

import static net.cjsah.mod.extendedoritech.ExtendedOritech.REGISTRATE;

public class ModBlocks {

    public static final BlockEntry<? extends Block> CHARACTER = REGISTRATE
        .block("machine_core_inf", p -> new MachineCoreBlock(p, 10))
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::noOcclusion)
        .lang("Creative Machine Core")
        .simpleItem()
        .register();


    public static void init() {
    }

}
