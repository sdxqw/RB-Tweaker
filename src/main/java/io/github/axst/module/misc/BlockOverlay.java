package io.github.axst.module.misc;

import io.github.axst.module.ModuleBase;
import io.github.axst.module.settings.misc.NumberSettings;

public class BlockOverlay extends ModuleBase {

    public NumberSettings outlineWidth = new NumberSettings("Line Width", "", 4.0f, 0.5f, 10.0f, 0.5f);

    public NumberSettings red = new NumberSettings("Red", "", 0, 0, 255, 1);
    public NumberSettings green = new NumberSettings("Green", "", 255, 0, 255, 1);
    public NumberSettings blue = new NumberSettings("Blue", "", 0, 0, 255, 1);
    public NumberSettings alpha = new NumberSettings("Alpha", "", 127.5, 0, 255, 1);

    public BlockOverlay() {
        super("Block Overlay",
                "Changes the look of the selection box",
                "discord");
        addSettings(outlineWidth, red, green, blue, alpha);
    }
}
