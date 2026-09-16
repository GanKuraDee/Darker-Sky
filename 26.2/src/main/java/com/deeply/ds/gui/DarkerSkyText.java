package com.deeply.ds.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;

/**
 * 設定画面の文言を組み立てます。
 *
 * <p>Mod の {@code assets/} 以下の言語ファイルを読み込むのは Fabric API の
 * fabric-resource-loader であり、この Mod は Fabric API を必須にしていません。
 * そのため Fabric API が無い環境では翻訳キーが解決されず、画面に
 * {@code darker-sky.title} のような生のキーが表示されてしまいます。</p>
 *
 * <p>そこで、翻訳が読み込まれている場合は通常どおり {@link Component#translatable}
 * を使い（リソースパックによる上書きも効きます）、読み込まれていない場合は
 * Java 側に持つ文言へフォールバックします。</p>
 */
public final class DarkerSkyText {
	private static final String KEY_TITLE = "darker-sky.title";
	private static final String KEY_BRIGHTNESS = "darker-sky.options.brightness";

	private DarkerSkyText() {
	}

	/** 画面タイトル。 */
	public static Component title() {
		return of(KEY_TITLE, "Darker Sky", "Darker Sky");
	}

	/** スライダーのラベル。{@code value} は "50%" のような表示用の文字列。 */
	public static Component brightness(String value) {
		return of(KEY_BRIGHTNESS, "Sky Brightness: " + value, "空の明るさ: " + value, value);
	}

	private static Component of(String key, String en, String ja, Object... args) {
		if (Language.getInstance().has(key)) {
			return Component.translatable(key, args);
		}
		return Component.literal(isJapanese() ? ja : en);
	}

	/** 現在選択中の言語が日本語か。ゲーム起動直後など判定できない場合は英語扱い。 */
	private static boolean isJapanese() {
		Minecraft client = Minecraft.getInstance();
		if (client == null || client.getLanguageManager() == null) {
			return false;
		}
		String code = client.getLanguageManager().getSelected();
		return code != null && code.startsWith("ja");
	}
}
