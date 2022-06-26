package io.github.axst.module.misc;

import io.github.axst.module.render.ModuleRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class FPSModule extends ModuleRenderer {

    public FPSModule() {
        super( "TestModule",
                   "test render module",
                   "discord", 100,
                   100);
    }

    @Override
    public int getHeightIn() {
        return fontRenderer.FONT_HEIGHT;
    }

    @Override
    public int getWidthIn() {
        return fontRenderer.getStringWidth( String.format("FPS: %d", Minecraft.getDebugFPS()) );
    }

    @Override
    public void drawModule() {
        Gui.drawRect(this.getX() - 2, this.getY() - 2, this.getX() + this.getWidthIn() + 2, this.getY() + this.getHeightIn() + 1, (new Color(0, 0, 0, 120)).getRGB());
        fontRenderer.drawString(String.format("FPS: %d", Minecraft.getDebugFPS()), this.getX(), this.getY(), -1);
    }

    @Override
    public void renderModule(int mouseX, int mouseY) {
        super.renderModule(mouseX, mouseY);
        Gui.drawRect(this.getX() - 2, this.getY() - 2, this.getX() + this.getWidthIn() + 2, this.getY() + this.getHeightIn() + 1, (new Color(0, 0, 0, 120)).getRGB());
        fontRenderer.drawString(String.format("FPS: %d", Minecraft.getDebugFPS()), this.getX(), this.getY(), -1);
    }
}
