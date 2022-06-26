package io.github.axst.ui.splash;

import io.github.axst.RebelCore;
import io.github.axst.utils.ui.PictureRenderer;
import io.github.axst.utils.ui.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SplashProgress {

    private static int PROGRESS = 0;
    private static String CURRENT = "";
    private static ResourceLocation splash;
    private static UnicodeFontRenderer ufr;


    public static void update() {
        if (Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null) {
            return;
        }
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void newMessage(String givenText) {
        ++PROGRESS;
        CURRENT = givenText;
        update();
    }

    public static void drawSplash(TextureManager tm) {

        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledresolution.getScaleFactor();

        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor, true);
        framebuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if (splash == null) {
        	splash = new ResourceLocation("rebelclient/bg.png");
        }

        tm.bindTexture(splash);

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 1920, 1080);
        drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);

        Minecraft.getMinecraft().updateDisplay();
    }

    private static void drawProgress() {

        if (Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null) {
        	return;
        }

        if(ufr == null) {
            ufr = UnicodeFontRenderer.getFontOnPC("Arial", 20);
        }

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        double scaledWidth = sr.getScaledWidth_double();
        double scaledHeight = sr.getScaledHeight_double();

        float width = 160.0f;
        float height = 80.0F;
        float x = (float) scaledWidth / 2.0f - 80.0f;
        float y = (float) scaledHeight - 40.0f;

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int MAX = 7;
        float loadedWidth = width * ((float) PROGRESS / (float) MAX);

        RenderUtils.drawRoundedRect(x - 40, y - 76, x + width + 40, y - height + 15, 9, new Color(80, 80, 80, 221).getRGB());
        RenderUtils.drawRoundedOutline((int) x- 40, (int) y - 76, (int) (x + width) + 40, (int) (y - height + 15), 9, 1,new Color(0, 0, 0, 255).getRGB());
        String step = "Mods (" + PROGRESS + "/" + MAX + ")";
        ufr.drawString(step, (sr.getScaledWidth() >> 1) + 20 - ufr.getStringWidth(step), sr.getScaledHeight() - 145, -1);

        if (PROGRESS != 0) {
            new PictureRenderer(sr.getScaledWidth() / 2 - 85, sr.getScaledHeight() / 2 - 75, 176, 42, new ResourceLocation("rebelclient/rclogo.png")).renderPicture();
            RenderUtils.drawRoundedRect(x - 40, y - 75.5f, x + loadedWidth + 40, y - height + 14.5f, 9, new Color(255,255,255).getRGB());
            ufr.drawString(CURRENT, (sr.getScaledWidth() >> 1) + 35 -  ufr.getStringWidth(CURRENT), sr.getScaledHeight() - 130, -1);
        }
    }
}