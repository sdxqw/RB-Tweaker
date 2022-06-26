package io.github.axst.ui.module;

import io.github.axst.RebelCore;
import io.github.axst.module.ModuleBase;
import io.github.axst.module.settings.Settings;
import io.github.axst.module.settings.misc.BooleanSettings;
import io.github.axst.ui.module.misc.ModuleButton;
import io.github.axst.ui.module.misc.SettingsButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public int x;
    public int y;
    public int w;
    public int h;

    public int scrollAmount = 0;

    public List<SettingsButton> setButtons = new ArrayList<>();
    public List<ModuleButton> modButtons = new ArrayList<>();

    public FrameType type = FrameType.MODULE;

    public ModuleBase mod;

    public Frame(int x, int y, int widthIn, int heightIn) {
        this.x = x;
        this.y = y;
        this.w = widthIn;
        this.h = heightIn;
    }

    public void drawScreen(int mouseX, int mouseY) {
        if(this.type == FrameType.MODULE) {
            this.modButtons.clear();
            int xAdd = 0;
            int xFactor = 100;
            int yAdd = 0;
            int spots = 0;
            while ((spots * xFactor) < (350)) {
                spots++;
            }
            for (ModuleBase m : RebelCore.getInstance().getModuleManager().getModules()) {
                if (xAdd == (spots * xFactor) && xAdd != 0) {
                    xAdd = 0;
                    yAdd += 40;
                }
                this.modButtons.add(new ModuleButton(x + 30 + xAdd, y + 30 + yAdd + scrollAmount, 90, 30, m));

                xAdd += xFactor;
            }

            GL11.glEnable(GL11.GL_SCISSOR_TEST);
            this.glScissor(x + 2, y + 25, w - 2, h - 2);
            for (ModuleButton m : modButtons) {
                m.drawButton();
            }
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
        } else if (this.type == FrameType.SETTINGS) {
            this.setButtons.clear();
            int yAdd1 = 0;
            for (Settings set : mod.getSetting()) {
                if(set instanceof BooleanSettings) {
                    this.setButtons.add(new SettingsButton(x + 10, y + 30 + yAdd1 + scrollAmount, 60, 20, set));
                    yAdd1 += 40;
                }
            }

            GL11.glEnable(GL11.GL_SCISSOR_TEST);
            this.glScissor(x + 2, y + 25, x + 450 - 2, h);
            for (SettingsButton m : setButtons) {
                m.drawButton(mouseX, mouseY);
            }
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
        }
    }

    public void setType(FrameType type, ModuleBase mod) {
        if(type == FrameType.MODULE) {
            this.type = FrameType.MODULE;
            this.mod = null;
        } else if(type == FrameType.SETTINGS) {
            this.type = FrameType.SETTINGS;
            this.mod = mod;
        }
    }

    private void glScissor(double x, double y, double width, double height) {

        y += height;

        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        Minecraft mc = Minecraft.getMinecraft();

        GL11.glScissor((int) ((x * mc.displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - y) * mc.displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * mc.displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * mc.displayHeight / scaledResolution.getScaledHeight()));
    }

    public void handleMouseInput() {

        int i = Integer.signum(Mouse.getEventDWheel());

        scrollAmount += (10 * i);
        if (scrollAmount > 0) scrollAmount = 0;
        if (scrollAmount < -100) scrollAmount = -100;

    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for(ModuleButton m : modButtons) {
            m.onClick(mouseX, mouseY, mouseButton);
        }
        for(SettingsButton s : setButtons) {
            s.onClick(mouseX, mouseY, mouseButton);
        }
    }
}
