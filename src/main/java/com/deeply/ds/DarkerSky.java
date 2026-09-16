package com.deeply.ds;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Darker Sky はクライアント専用の見た目 Mod です。
 *
 * <p>空の描画色を Java 側 (Mixin) で暗くします。暗さ係数 {@link #getDarkness()} は
 * 設定ファイル・設定画面 (<code>/darkersky</code>) から実行時に変更でき、
 * Mixin が毎フレームこの値を参照するため即座に反映されます。</p>
 *
 * <ul>
 *   <li>{@code SkyRenderer#renderSkyDisc(int)} … 空ドームの色</li>
 *   <li>{@code FogRenderer#computeFogColor(...)} … 大気フォグ色（= 地平線帯のクリア色）</li>
 * </ul>
 *
 * <p>{@code /darkersky} コマンドは fabric-api を使わず、
 * {@code ChatScreenMixin} がチャット入力を横取りして設定画面を開きます。</p>
 */
public class DarkerSky implements ClientModInitializer {
	public static final String MOD_ID = "darker-sky";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	/** 設定が無い場合の初期値。1.0=バニラ / 0.5=明るさ半分 / 0.0=真っ暗。 */
	public static final float DEFAULT_DARKNESS = 0.5F;

	/** 現在の空の明るさ係数 (0.0〜1.0)。設定画面・config から変更される。 */
	private static volatile float darkness = DEFAULT_DARKNESS;

	public static float getDarkness() {
		return darkness;
	}

	/** 明るさ係数を 0.0〜1.0 にクランプして設定します。 */
	public static void setDarkness(float value) {
		darkness = Math.max(0.0F, Math.min(1.0F, value));
	}

	@Override
	public void onInitializeClient() {
		DarkerSkyConfig.load();
		LOGGER.info("[Darker Sky] Loaded. Type /darkersky in chat to open the settings screen. Current brightness = {}", darkness);
	}

	/**
	 * ARGB カラーの RGB を現在の {@link #getDarkness()} 倍します (アルファは維持)。
	 * Minecraft のクラスに依存しない純粋なビット演算です。
	 */
	public static int darken(int argb) {
		float d = darkness;
		int a = (argb >>> 24) & 0xFF;
		int r = (int) (((argb >> 16) & 0xFF) * d);
		int g = (int) (((argb >> 8) & 0xFF) * d);
		int b = (int) ((argb & 0xFF) * d);
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}
