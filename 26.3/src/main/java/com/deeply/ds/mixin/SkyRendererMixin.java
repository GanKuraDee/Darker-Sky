package com.deeply.ds.mixin;

import com.deeply.ds.DarkerSky;

import net.minecraft.client.renderer.SkyRenderer;

import org.joml.Vector3fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * 空ドームの色を暗くします。
 *
 * <p>{@code renderSkyDisc(RenderPass, Vector3fc skyColor)} の引数 {@code skyColor} は
 * 空ドーム（上側のディスク）の基本色 (ColorModulator) です。これを
 * DARKNESS 倍することで天頂側が暗くなります。地平線側はフォグ色
 * ({@link FogRendererMixin}) が同じ係数で暗くなるため、全体が均一になります。</p>
 *
 * <p>26.2 までは {@code renderSkyDisc(int skyColor)} で ARGB int を受け取っていましたが、
 * 26.3 で描画が RenderPass 方式になり、色は {@code SkyRenderState#skyColor} 由来の
 * {@link Vector3fc} で渡されるようになりました。元の {@code Vector3fc} は
 * RenderState が保持しているため、破壊的に書き換えず新しいベクトルを返します。</p>
 */
@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
	@ModifyVariable(method = "renderSkyDisc", at = @At("HEAD"), argsOnly = true, index = 2)
	private Vector3fc darkerSky$darkenSkyColor(Vector3fc skyColor) {
		return DarkerSky.darken(skyColor);
	}
}
