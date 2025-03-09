package com.cobblemonuitweaks.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "cobblemon-ui-tweaks")
@Config(name = "cobblemonUiTweaks", wrapperName = "CobblemonUiTweaksConfig")
public class ModConfig {
    public boolean ShowBattleTypes = true;
}
