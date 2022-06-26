package io.github.axst.module.settings.misc;

import io.github.axst.module.settings.Settings;
import lombok.Getter;
import lombok.Setter;

public class NumberSettings extends Settings {

    @Getter
    public double value;
    @Getter @Setter
    public double minimum;
    @Getter @Setter
    public double maximum;
    @Getter
    public double increment;

    public NumberSettings(String name, String description, double value, double minimum, double maximum, double increment) {
        super(name, description);
        this.name = name;
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = increment;
    }

    public void setValue(double value) {
        double precision = 1 / increment;
        this.value = Math.round(Math.max(minimum, Math.min(maximum, value)) * precision) / precision;
    }

    public void setIncrement(boolean positive) {
        setValue(getValue() + (positive ? 1 : -1) * increment);
    }
}
