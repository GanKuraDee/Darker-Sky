package com.deeply.ds.gui;

import com.deeply.ds.DarkerSky;
import com.deeply.ds.DarkerSkyConfig;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * {@code /darkersky} で開く設定画面。スライダーで空の明るさを調整します。
 * スライダーを動かすと即座に空へ反映され、閉じるときに設定ファイルへ保存します。
 */
public class DarkerSkyScreen extends Screen {
	private static final Component TITLE = Component.translatable("darker-sky.title");

	public DarkerSkyScreen() {
		super(TITLE);
	}

	@Override
	protected void init() {
		int centerX = this.width / 2;
		int centerY = this.height / 2;

		this.addRenderableWidget(new StringWidget(centerX - 100, centerY - 50, 200, 20, TITLE, this.font));
		this.addRenderableWidget(new DarknessSlider(centerX - 100, centerY - 20, 200, 20));
		this.addRenderableWidget(
			Button.builder(Component.translatable("gui.done"), button -> this.onClose())
				.bounds(centerX - 100, centerY + 10, 200, 20)
				.build());
	}

	@Override
	public void onClose() {
		DarkerSkyConfig.save();
		// 実際の画面クローズはバニラ実装に委譲（版差を吸収）
		super.onClose();
	}

	/** 空の明るさ (0%〜100% = {@link DarkerSky#getDarkness()} の 0.0〜1.0) を調整するスライダー。 */
	private static final class DarknessSlider extends AbstractSliderButton {
		DarknessSlider(int x, int y, int width, int height) {
			super(x, y, width, height, Component.empty(), DarkerSky.getDarkness());
			this.updateMessage();
		}

		@Override
		protected void updateMessage() {
			int percent = Math.round((float) this.value * 100.0F);
			this.setMessage(Component.translatable("darker-sky.options.brightness", percent + "%"));
		}

		@Override
		protected void applyValue() {
			// スライダー操作を即座に空へ反映（保存は閉じるとき）
			DarkerSky.setDarkness((float) this.value);
		}
	}
}
