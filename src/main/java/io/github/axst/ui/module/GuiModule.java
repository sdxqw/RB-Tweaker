package io.github.axst.ui.module;

import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiModule extends GuiScreen {

    public Frame frame;

    @Override
    public void initGui() {
        super.initGui();
        frame = new Frame(this.width / 2 - 226, this.height / 2 - 150, this.width / 2 + 225, this.height / 2 + 150);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        frame.drawScreen(mouseX, mouseY);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        mc.entityRenderer.stopUseShader();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        frame.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        frame.handleMouseInput();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
