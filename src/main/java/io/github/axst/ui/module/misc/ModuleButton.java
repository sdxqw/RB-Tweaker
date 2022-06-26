package io.github.axst.ui.module.misc;

import io.github.axst.RebelCore;
import io.github.axst.module.ModuleBase;
import io.github.axst.ui.module.FrameType;
import io.github.axst.utils.interfaces.IHelper;
import io.github.axst.utils.ui.RenderUtils;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModuleButton {

    public int x;
    public int y;
    public int w;
    public int h;

    public ModuleBase mod;

    public ModuleButton(int x, int y, int widthIn, int heightIn, ModuleBase module) {
        this.x = x;
        this.y = y;
        this.w = widthIn;
        this.h = heightIn;
        this.mod = module;
    }

    public void drawButton() {
        RenderUtils.drawSmoothRoundedRect(x, y, x + w, y + h, 10, new Color(55, 55, 55).getRGB());
        Gui.drawRect(x + w - 20, y, x + w, y + h, getColor());
        IHelper.fontRenderer.drawString(mod.getName(), x + w - 20 - IHelper.fontRenderer.getStringWidth(mod.getName()) - 5, y + h / 2 - IHelper.fontRenderer.FONT_HEIGHT / 2, -1);
    }

    private int getColor() {
        if(mod.isEnabled()) {
            return new Color(32, 189, 45, 255).getRGB();
        } else {
            return new Color(189, 32, 32).getRGB();
        }
    }

    public void onClick(int mouseX, int mouseY, int button) {
        if(mouseX >= x && mouseY >= y && mouseX < x + w && mouseY < y + h) {
            if(button == 0) {
                mod.toggleModule();
            } else if (button == 1) {
                RebelCore.getInstance().getGuiModule().frame.setType(FrameType.SETTINGS, mod);
            }
        }
    }
}
