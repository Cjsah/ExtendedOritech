package net.cjsah.mod.extendedoritech.datagen;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.cjsah.mod.extendedoritech.init.ModBlocks;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import rearth.oritech.init.BlockContent;

public class ModRecipeProvider {
    public static void register(RegistrateRecipeProvider provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PLUGIN_ADDON_EXTENDER)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('A', BlockContent.MACHINE_EXTENDER)
            .define('B', BlockContent.MACHINE_CORE_7)
            .unlockedBy("has_machine_extender", RegistrateRecipeProvider.has(BlockContent.MACHINE_EXTENDER))
            .unlockedBy("has_machine_core_7", RegistrateRecipeProvider.has(BlockContent.MACHINE_CORE_7))
            .save(provider);
    }
}
