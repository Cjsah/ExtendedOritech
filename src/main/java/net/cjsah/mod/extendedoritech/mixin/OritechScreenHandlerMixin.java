package net.cjsah.mod.extendedoritech.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rearth.oritech.client.ui.UpgradableMachineScreen;

@Mixin(UpgradableMachineScreen.class)
public class OritechScreenHandlerMixin {

    @ModifyExpressionValue(method = "fillOverlay", at = @At(value = "INVOKE", target = "Lrearth/oritech/util/MachineAddonController;getCoreQuality()F"))
    private float modifyMachineUpgradeCount(float original) {
        return Math.min(original, 6f);
    }
}
