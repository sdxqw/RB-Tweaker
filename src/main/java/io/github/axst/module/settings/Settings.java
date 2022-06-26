package io.github.axst.module.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
public class Settings {
    @Getter
    public String name;
    @Getter
    public String description;
}
