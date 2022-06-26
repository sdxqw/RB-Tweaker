package io.github.axst;

import io.github.axst.module.ModuleManager;
import io.github.axst.ui.module.GuiModule;
import io.github.axst.ui.splash.SplashProgress;
import io.github.axst.utils.font.CFontRenderer;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;

public class RebelCore {


    @Getter private static final RebelCore instance = new RebelCore();
    @Getter private ModuleManager moduleManager;
    @Getter public GuiModule guiModule;

    public CFontRenderer clientFont;
    public CFontRenderer clientFontSmaller;
    public CFontRenderer clientFontBoldSmaller;

    public void initialize() {
        SplashProgress.newMessage("some cool feature");
        moduleManager = new ModuleManager();
        guiModule = new GuiModule();
        loadFonts();
    }

    public void loadFonts() {
        clientFont = new CFontRenderer(new ResourceLocation("rebelclient/fonts/panton-rust-black-base.ttf"), 20);
        clientFontSmaller = new CFontRenderer(new ResourceLocation("rebelclient/fonts/Quicksand-Light.ttf"), 18);
        clientFontBoldSmaller = new CFontRenderer( new ResourceLocation("rebelclient/fonts/Ubuntu-Bold.ttf"), 20 );
    }
}
