package com.cobblemonuitweaks.mixin.client;

import com.cobblemon.mod.common.api.pokedex.PokedexEntryProgress;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
import com.cobblemon.mod.common.client.battle.ClientBallDisplay;
import com.cobblemon.mod.common.client.gui.battle.BattleOverlay;
import com.cobblemon.mod.common.client.gui.TypeIcon;
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Species;
import com.cobblemon.mod.common.pokemon.status.PersistentStatus;
import com.cobblemonuitweaks.CobblemonUiTweaksClient;
import kotlin.Triple;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.StreamSupport;

@Mixin(BattleOverlay.class)
public abstract class BattleOverlayMixin {

	@Inject(method = "drawBattleTile", at = @At("TAIL"))
	private void injectPokemonTypeDisplay(
			GuiGraphics context, float x, float y, float partialTicks, boolean reversed, Species species, int level, MutableComponent displayName, Gender gender, PersistentStatus status, PosableState state, Triple<Float, Float, Float> colour, float opacity, ClientBallDisplay ballState, int maxHealth, float health, boolean isSelected, boolean isHovered, boolean isCompact, MutableComponent actorDisplayName, boolean isFlatHealth, PokedexEntryProgress dexState, CallbackInfo ci
	) {
		// Only show types in enabled in config.
		if(!CobblemonUiTweaksClient.CONFIG.ShowBattleTypes()) return;

		List<ElementalType> types = StreamSupport.stream(species.getTypes().spliterator(), false).toList();
		if (types.isEmpty()) return;

		// Get portrait positions
		int portraitOffsetX = isCompact ? BattleOverlay.COMPACT_PORTRAIT_OFFSET_X : BattleOverlay.PORTRAIT_OFFSET_X;
		int portraitDiameter = isCompact ? BattleOverlay.COMPACT_PORTRAIT_DIAMETER : BattleOverlay.PORTRAIT_DIAMETER;
		int horizontalInset = BattleOverlay.HORIZONTAL_INSET;
		int tileHeight = isCompact ? BattleOverlay.COMPACT_TILE_HEIGHT : BattleOverlay.TILE_HEIGHT;
		int tileWidth = isCompact ? BattleOverlay.COMPACT_TILE_WIDTH : BattleOverlay.TILE_WIDTH;
		float iconRadius = 4.5F;

		// Position type icons
		float iconOffsetX = !reversed ? portraitOffsetX + ((float) portraitDiameter / 2) - iconRadius : tileWidth - portraitOffsetX - ((float) portraitDiameter / 2) - iconRadius;
		int iconOffsetY = tileHeight;

		// Use TypeIcon class to render the icons
		new TypeIcon(
				x + iconOffsetX,
				y + iconOffsetY,
				types.get(0),
				types.size() > 1 ? types.get(1) : null,
				types.size() > 1,
				true,
				9F,
				0F,
				1F
		).render(context);
	}
}
