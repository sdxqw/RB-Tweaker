package io.github.axst.ui;

import io.github.axst.module.render.ModuleRenderer;
import net.minecraft.client.gui.GuiScreen;

public class HUDScreen extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ModuleRenderer.renderComponent(mouseX, mouseY);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
