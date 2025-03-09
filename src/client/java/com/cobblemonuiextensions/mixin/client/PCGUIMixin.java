package com.cobblemonuiextensions.mixin.client;

import com.cobblemon.mod.common.client.gui.pc.PCGUI;
import com.cobblemon.mod.common.client.gui.pc.StorageWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PCGUI.class)
public abstract class PCGUIMixin {

    @Shadow private StorageWidget storageWidget;

    @Inject(method = "mouseScrolled", at = @At("TAIL"))
    private void injectPCScrollControl(
            double mouseX, double mouseY, double amount, double verticalAmount, CallbackInfoReturnable<Boolean> cir
    ) {
        this.storageWidget.setBox((int) (this.storageWidget.getBox() + verticalAmount));
    }

    @Inject(method="init", at = @At("TAIL"))
    private void injectInit(CallbackInfo ci) {
        this.storageWidget.setBox(0);
    }
}
