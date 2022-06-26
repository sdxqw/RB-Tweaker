package io.github.axst.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class KeyBinds {

    public static KeyBinding HUD_SCREEN = new KeyBinding("HUD Screen", Keyboard.KEY_RSHIFT, "DarkClient");
    public static KeyBinding TOGGLE_PERSPECTIVE = new KeyBinding("Toggle Perspective", Keyboard.KEY_R, "DarkClient");

    public KeyBinds() {
        registerKeyBindings(HUD_SCREEN);
        registerKeyBindings(TOGGLE_PERSPECTIVE);
    }

    public void registerKeyBindings(final KeyBinding keyBinds) {
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, keyBinds);
    }
}
