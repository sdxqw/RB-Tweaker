package io.github.axst.utils.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.world.World;

public interface IHelper {
    Minecraft minecraft = Minecraft.getMinecraft();
    World world = minecraft.theWorld;
    EntityPlayerSP thePlayer = minecraft.thePlayer;
    FontRenderer fontRenderer = minecraft.fontRendererObj;
}
