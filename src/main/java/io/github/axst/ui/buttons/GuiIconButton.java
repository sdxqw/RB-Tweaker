package io.github.axst.ui.buttons;

import io.github.axst.utils.ui.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiIconButton extends GuiButton {

    private final ResourceLocation ICON;

    public GuiIconButton(final int buttonId, final int x, final int y, final int width, final int height, final String iconName) {
        super(buttonId, x, y, width, height, "");
        this.ICON = new ResourceLocation("rebelclient/icons/" + iconName);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.pushMatrix();
            GlStateManager.enableAlpha();
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 2,new Color(164, 172, 180, 255).getRGB());
            RenderUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, new Color(164, 172, 180, 64).getRGB());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            GL11.glColor3f(255, 255, 255);
            mc.getTextureManager().bindTexture(ICON);
            Gui.drawModalRectWithCustomSizedTexture(this.xPosition + (this.width - 14) / 2, this.yPosition + (this.height - 14) / 2, 0, 0, 14, 14, (float) 14, (float) 14);
            GlStateManager.popMatrix();
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
