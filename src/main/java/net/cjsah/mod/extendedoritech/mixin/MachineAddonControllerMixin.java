package net.cjsah.mod.extendedoritech.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rearth.oritech.util.MachineAddonController;

import java.util.List;

@Mixin(MachineAddonController.class)
public class MachineAddonControllerMixin {
    @Inject(method = "gatherAddonStats", at = @At(value = "INVOKE", target = "Lrearth/oritech/util/MachineAddonController;getAdditionalStatFromAddon(Lrearth/oritech/util/MachineAddonController$AddonBlock;)V", ordinal = 1))
    private void inject(
        List<MachineAddonController.AddonBlock> addons,
        CallbackInfo ci
//        @Local(ordinal = 0, print = true) LocalRef<Float> speed,
//        @Local(ordinal = 1, print = true) LocalRef<Float> efficiency,
//        @Local(ordinal = 0, print = true) LocalRef<Long> energyAmount,
//        @Local(ordinal = 1, print = true) LocalRef<Long> energyInsert,
//        @Local(ordinal = 0, print = true) LocalRef<Integer> extraChambers,
//        @Local(ordinal = 1, print = true) LocalRef<Integer> extraBurstTicks
    ) {
        System.out.println(1);

    }
}
