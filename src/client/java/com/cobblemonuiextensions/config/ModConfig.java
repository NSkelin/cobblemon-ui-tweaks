package com.cobblemonuiextensions.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "cobblemon-ui-extensions")
@Config(name = "CobblemonUIExtensions", wrapperName = "CobblemonUIExtensionsConfig")
public class ModConfig {
    public boolean ShowBattleTypes = true;
    public boolean ReversePCScrollDirection = false;
}
