package com.deeply.ds.modmenu;

import com.deeply.ds.gui.DarkerSkyScreen;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

/**
 * Mod Menu 連携。Mod Menu の Mod 一覧でこの Mod の設定アイコンを押すと
 * {@link DarkerSkyScreen} が開きます。
 *
 * <p>Mod Menu は任意依存です。未導入の環境では {@code modmenu} エントリポイントは
 * 呼び出されないため、このクラスがロードされることもありません。</p>
 */
public class DarkerSkyModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		// parent（Mod Menu の一覧画面）を渡すと、閉じたときにそこへ戻ります。
		return DarkerSkyScreen::new;
	}
}
