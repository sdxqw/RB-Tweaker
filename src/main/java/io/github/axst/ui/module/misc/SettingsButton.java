package io.github.axst.ui.module.misc;

import io.github.axst.RebelCore;
import io.github.axst.module.settings.Settings;
import io.github.axst.module.settings.misc.BooleanSettings;
import io.github.axst.utils.interfaces.IHelper;
import io.github.axst.utils.ui.RenderUtils;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class SettingsButton {
    public int x;
    public int y;
    public int w;
    public int h;

    public Settings set;

    public SettingsButton(int x, int y, int widthIn, int heightIn, Settings settings) {
        this.x = x;
        this.y = y;
        this.w = widthIn;
        this.h = heightIn;
        this.set = settings;
    }

    public void drawButton(int mouseX, int mouseY) {
        if(set instanceof BooleanSettings) {
            IHelper.fontRenderer.drawString(set.name, x, y+ (h >> 1) - (IHelper.fontRenderer.FONT_HEIGHT >> 1), -1);

            Gui.drawRect(x + IHelper.fontRenderer.getStringWidth(set.name) + 4, (int) (y + 2.5f), x + IHelper.fontRenderer.getStringWidth(set.name) + 15 + 4, (int) (y + h - 2.5f), new Color(117, 117, 117).getRGB());

            if(((BooleanSettings) set).isEnabled()) {
                Gui.drawRect(x + IHelper.fontRenderer.getStringWidth(set.name) + 6, (int) (y + 4.5f), x + IHelper.fontRenderer.getStringWidth(set.name) + 15 + 2, (int) (y + h - 4.5f), new Color(60, 160, 50).getRGB());
            }
        }
    }

    public void onClick(int mouseX, int mouseY, int mouseButton) {
        if(set instanceof BooleanSettings) {
            if(mouseX >= x + RebelCore.getInstance().clientFontSmaller.getStringWidth(set.name) + 4 && mouseX <= x + RebelCore.getInstance().clientFontSmaller.getStringWidth(set.name) + 4 + 15 && mouseY >= y && mouseY <= y + h) {
                ((BooleanSettings) set).setEnabled(!((BooleanSettings) set).isEnabled());
            }
        }
    }
}
