package com.deeply.ds.mixin;

import com.deeply.ds.DarkerSky;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;

import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 大気フォグ色を暗くします。
 *
 * <p>{@code computeFogColor} が書き込む {@code dest} (Vector4f) は、
 * FogColor uniform（地形・空ドーム・雲などのフォグ混色先）と、
 * {@code LevelRenderer} がフレームバッファをクリアする色（＝地平線に見える
 * 明るい帯）の<b>両方</b>の元になります。ここで RGB を DARKNESS 倍すれば、
 * その両方が同時に暗くなります。</p>
 */
@Mixin(FogRenderer.class)
public class FogRendererMixin {
	@Inject(method = "computeFogColor", at = @At("TAIL"))
	private void darkerSky$darkenFogColor(Camera camera, float partialTicks, ClientLevel level,
			int renderDistance, float darkenWorldAmount, Vector4f dest, CallbackInfo ci) {
		float d = DarkerSky.getDarkness();
		dest.set(dest.x * d, dest.y * d, dest.z * d, dest.w);
	}
}
