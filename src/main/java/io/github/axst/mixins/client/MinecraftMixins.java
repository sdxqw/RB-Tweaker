package io.github.axst.mixins.client;

import io.github.axst.RebelCore;
import io.github.axst.ui.HUDScreen;
import io.github.axst.ui.module.GuiModule;
import io.github.axst.ui.splash.SplashProgress;
import io.github.axst.utils.KeyBinds;
import io.github.axst.utils.interfaces.IHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;
import java.nio.ByteBuffer;

@Mixin(Minecraft.class)
public abstract class MinecraftMixins {

    @Shadow
    @Final
    private DefaultResourcePack mcDefaultResourcePack;

    @Inject(method = "startGame", at = @At(value = "RETURN"))
    public void startGameAfter(CallbackInfo ci) {
        RebelCore.getInstance().initialize();
    }

    @Redirect(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;drawSplashScreen(Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void showSplashScreen(Minecraft minecraft, TextureManager textureManagerInstance) {
        SplashProgress.drawSplash(minecraft.getTextureManager());
    }

    @ModifyConstant(method = "createDisplay()V", constant = @Constant(stringValue = "Minecraft 1.8.9"))
    public String createDisplay(String constant) {
        return "Rebel Client | v1-1.8.9";
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z"))
    public void injectRuntTuck2(final CallbackInfo ci) {
        if (KeyBinds.HUD_SCREEN.isPressed()) Minecraft.getMinecraft().displayGuiScreen(new HUDScreen());
        if (KeyBinds.TOGGLE_PERSPECTIVE.isPressed()) IHelper.minecraft.displayGuiScreen(new GuiModule());
    }

    /**
     * @author sdxqw
     * @reason icon
     */
    @Overwrite
    private void setWindowIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try {
                InputStream inputStream = MinecraftMixins.class.getResourceAsStream("/assets/minecraft/rebelclient/icons/16x.png");
                InputStream inputStream2 = MinecraftMixins.class.getResourceAsStream("/assets/minecraft/rebelclient/icons/32x.png");
                if (inputStream == null) {
                    inputStream = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("icons/icon_16x16.png"));
                }
                if (inputStream2 == null) {
                    inputStream2 = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("icons/icon_32x32.png"));
                }
                Display.setIcon(new ByteBuffer[] { this.readImageToBuffer(inputStream), this.readImageToBuffer(inputStream2) });
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Shadow
    protected abstract ByteBuffer readImageToBuffer(final InputStream inputStream);
}
