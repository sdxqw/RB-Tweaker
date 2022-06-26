package io.github.axst.module.settings.misc;

import io.github.axst.module.settings.Settings;

import java.util.Arrays;
import java.util.List;

public class ModeSettings extends Settings {

    public int index;

    public List<String> modes;

    public ModeSettings(String name, String description, String defaultMode, String... modes) {
        super(name, description);
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }

    public String getMode() {
        return this.modes.get(this.index);
    }

    public boolean getMode(String mode) {
        return this.index == this.modes.indexOf(mode);
    }

    public void increment() {
        if (this.index < this.modes.size() - 1) {
            this.index++;
        } else {
            this.index = 0;
        }
    }
}
