package net.cjsah.mod.extendedoritech.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.cjsah.mod.extendedoritech.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rearth.oritech.init.BlockEntitiesContent;

import java.util.Arrays;

@Mixin(BlockEntitiesContent.class)
public class OritechBlockEntityMixin {
    @WrapOperation(
        method = "<clinit>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/entity/BlockEntityType$Builder;of(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;[Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/entity/BlockEntityType$Builder;",
            ordinal = 58
        )
    )
    private static <T extends BlockEntity> BlockEntityType.Builder<T> inject(BlockEntityType.BlockEntitySupplier<T> supplier, Block[] blocks, Operation<BlockEntityType.Builder<T>> original) {
        Block[] extendedBlocks = Arrays.copyOf(blocks, blocks.length + 1);
        extendedBlocks[blocks.length] = ModBlocks.MACHINE_CORE_INF.get();
        return original.call(supplier, extendedBlocks);
    }

}
