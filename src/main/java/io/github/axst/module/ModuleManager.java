package io.github.axst.module;

import io.github.axst.module.misc.BlockOverlay;
import io.github.axst.module.misc.FPSModule;
import io.github.axst.module.render.ModuleRenderer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    @Getter
    public List<ModuleBase> modules;

    public BlockOverlay blockOverlay;

    public ModuleManager() {
        modules = new ArrayList<>();
        modules.add(blockOverlay = new BlockOverlay());
        modules.add(new FPSModule());
    }

    public void renderHooks() {
        modules.forEach(module -> {
            if (module.isEnabled() && module instanceof ModuleRenderer)
                ((ModuleRenderer)module).drawModule();
        });
    }
}
