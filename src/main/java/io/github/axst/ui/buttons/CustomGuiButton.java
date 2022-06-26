package io.github.axst.ui.buttons;

import io.github.axst.RebelCore;
import io.github.axst.utils.ui.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

public class CustomGuiButton extends GuiButton {

    public CustomGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super( buttonId, x, y, widthIn, heightIn, buttonText );
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 2,new Color(164, 172, 180, 255).getRGB());
            RenderUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, new Color(164, 172, 180, 64).getRGB());
            RebelCore.getInstance().clientFont.drawCenteredString(this.displayString, this.xPosition + (this.width >> 1), this.yPosition + ((this.height - 8) >> 1) + 4, -1);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
