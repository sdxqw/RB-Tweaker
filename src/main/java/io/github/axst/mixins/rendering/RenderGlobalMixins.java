package io.github.axst.mixins.rendering;

import io.github.axst.RebelCore;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class RenderGlobalMixins {

    @Inject(method = "drawSelectionBox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", shift = At.Shift.AFTER))
    private void drawSelectionBox(EntityPlayer player, MovingObjectPosition movingObjectPositionIn, int execute, float partialTicks, CallbackInfo ci) {
        if (RebelCore.getInstance().getModuleManager().blockOverlay.isEnabled()) {
            GlStateManager.color((float) RebelCore.getInstance().getModuleManager().blockOverlay.red.getValue(), (float) RebelCore.getInstance().getModuleManager().blockOverlay.green.getValue(), (float) RebelCore.getInstance().getModuleManager().blockOverlay.blue.getValue());
        }
    }

    @ModifyArg(method = "drawSelectionBox", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glLineWidth(F)V", remap = false))
    private float getLineWidth(float lineWidth) {
        return RebelCore.getInstance().getModuleManager().blockOverlay.isEnabled() ? (float) RebelCore.getInstance().getModuleManager().blockOverlay.outlineWidth.getValue() : lineWidth;
    }

}
