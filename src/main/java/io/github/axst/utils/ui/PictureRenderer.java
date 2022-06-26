package io.github.axst.utils.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class PictureRenderer {

    public int x, y, width, height;
    public ResourceLocation resourceLocation;

    public PictureRenderer(int x, int y, int width, int height, ResourceLocation resourceLocation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.resourceLocation = resourceLocation;
    }

    public void renderPicture() {
        GlStateManager.enableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
        GlStateManager.disableAlpha();
    }
}