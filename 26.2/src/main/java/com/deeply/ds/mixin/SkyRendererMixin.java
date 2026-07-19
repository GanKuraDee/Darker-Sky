package com.deeply.ds.mixin;

import com.deeply.ds.DarkerSky;

import net.minecraft.client.renderer.SkyRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * 空ドームの色を暗くします。
 *
 * <p>{@code renderSkyDisc(int skyColor)} の引数 {@code skyColor} は
 * 空ドーム（上側のディスク）の基本色 (ColorModulator) です。これを
 * DARKNESS 倍することで天頂側が暗くなります。地平線側はフォグ色
 * ({@link FogRendererMixin}) が同じ係数で暗くなるため、全体が均一になります。</p>
 */
@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
	@ModifyVariable(method = "renderSkyDisc(I)V", at = @At("HEAD"), argsOnly = true, index = 1)
	private int darkerSky$darkenSkyColor(int skyColor) {
		return DarkerSky.darken(skyColor);
	}
}
