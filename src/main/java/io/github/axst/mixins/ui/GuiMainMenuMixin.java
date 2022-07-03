package io.github.axst.mixins.ui;

import io.github.axst.RebelCore;
import io.github.axst.ui.buttons.CustomGuiButton;
import io.github.axst.ui.buttons.GuiIconButton;
import io.github.axst.utils.LinkOpener;
import io.github.axst.utils.LoggerUtils;
import io.github.axst.utils.interfaces.IHelper;
import io.github.axst.utils.ui.RenderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.awt.*;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin extends GuiScreen implements IHelper {

    /**
     * GuiMainMenu Method Overwrite.
     * @author sdxqw
     * @reason Optimized
     */
    @Override
    @Overwrite
    public void initGui(){
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.buttonList.add(new CustomGuiButton(1, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2, 170, 20, "SINGLEPLAYER"));
        this.buttonList.add(new CustomGuiButton(2, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2 + 25, 170, 20, "MULTIPLAYER"));
        this.buttonList.add(new CustomGuiButton( 3, sr.getScaledWidth() / 2 - 30, sr.getScaledHeight() / 2 + 50, 70, 20, "QUIT" ));
        this.buttonList.add(new GuiIconButton(4, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2 + 50, 20, 20, "cosmetics.png"));
        this.buttonList.add(new GuiIconButton(5, sr.getScaledWidth() / 2 + 70, sr.getScaledHeight() / 2 + 50, 20, 20, "discord.png"));
        this.buttonList.add(new GuiIconButton(6, sr.getScaledWidth() / 2 + 45, sr.getScaledHeight() / 2 + 50, 20, 20, "language.png"));
        this.buttonList.add(new GuiIconButton(7, sr.getScaledWidth() / 2 - 55, sr.getScaledHeight() / 2 + 50, 20, 20, "options.png"));
    }

    /**
     * GuiMainMenu Method Overwrite.
     * @author sdxqw
     * @reason Optimized
     */
    @Override
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F,1.0F);
        RenderUtils.renderProfile();
        GlStateManager.popMatrix();
        String watermark = "Rebel Client (1.8.9-v1)";
        RebelCore.getInstance().clientFontBoldSmaller.drawString(watermark,4, this.height - 10, new Color( 230, 230, 230, 157 ).getRGB());
        String copyright = "Copyright Mojang AB. Do not distribute!";
        RebelCore.getInstance().clientFontBoldSmaller.drawString(copyright, this.width - this.fontRendererObj.getStringWidth(copyright) - 6, this.height - 10, new Color( 230, 230, 230, 157 ).getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * GuiMainMenu Method Overwrite.
     * @author sdxqw
     * @reason Optimized
     */
    @Override
    @Overwrite
    protected void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 1: minecraft.displayGuiScreen( new GuiSelectWorld( this ) );
            break;
            case 2: minecraft.displayGuiScreen( new GuiMultiplayer( this ) );
            break;
            case 3: minecraft.shutdown();
            break;
            case 5: LinkOpener.openLink("https://discord.gg/MQQNx5HTZx");
            break;
            case 6: minecraft.displayGuiScreen( new GuiLanguage( this, minecraft.gameSettings, minecraft.getLanguageManager() ) );
            break;
            case 7: minecraft.displayGuiScreen( new GuiOptions( this, minecraft.gameSettings ) );
            break;
            default: LoggerUtils.error("This button don't exists.");
            break;
        }
    }
}
