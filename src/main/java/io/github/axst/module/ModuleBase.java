package io.github.axst.module;

import io.github.axst.module.settings.Settings;
import io.github.axst.utils.interfaces.IHelper;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleBase implements IHelper {

    @Getter
    private final String name;
    @Getter
    private final String description;

    @Getter
    private final ResourceLocation icon;

    @Getter @Setter
    private boolean enabled;

    @Getter
    public final List<Settings> setting = new ArrayList<>();

    public ModuleBase(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = new ResourceLocation("rebelclient/icons/" + icon + ".png");
        setEnabled(true);
    }

    public void toggleModule() {
        this.setEnabled(!this.isEnabled());
    }


    public void addSettings(Settings... toAdd) {
        setting.addAll(Arrays.asList(toAdd));
    }
}
