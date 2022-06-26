package io.github.axst.mixins.ui;

import io.github.axst.RebelCore;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public final class GuiIngameMixins {
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void runTick (CallbackInfo info) {
        RebelCore.getInstance().getModuleManager().renderHooks();
    }
}
