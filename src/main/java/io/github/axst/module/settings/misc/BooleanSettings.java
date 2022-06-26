package io.github.axst.module.settings.misc;

import io.github.axst.module.settings.Settings;
import lombok.Getter;
import lombok.Setter;

public class BooleanSettings extends Settings {
    @Getter @Setter
    private boolean enabled;

    public BooleanSettings(String name, String description, boolean enabled) {
        super(name, description);
        this.enabled = enabled;
    }
}
