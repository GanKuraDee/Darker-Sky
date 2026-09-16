package com.deeply.ds;

import net.fabricmc.loader.api.FabricLoader;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * 空の明るさ係数を config フォルダの {@code darker-sky.properties} に保存/読込します。
 * Minecraft のクラスには依存しません（Fabric Loader API + JDK のみ）。
 */
public final class DarkerSkyConfig {
	private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("darker-sky.properties");
	private static final String KEY = "darkness";

	private DarkerSkyConfig() {
	}

	/** 設定を読み込んで {@link DarkerSky#setDarkness(float)} に反映します。無ければ初期値で作成します。 */
	public static void load() {
		try {
			if (Files.exists(FILE)) {
				Properties props = new Properties();
				try (InputStream in = Files.newInputStream(FILE)) {
					props.load(in);
				}
				String value = props.getProperty(KEY);
				if (value != null) {
					DarkerSky.setDarkness(Float.parseFloat(value.trim()));
				}
			} else {
				save();
			}
		} catch (Exception e) {
			DarkerSky.LOGGER.warn("[Darker Sky] Failed to load config; using the default value", e);
		}
	}

	/** 現在の明るさ係数をファイルに書き出します。 */
	public static void save() {
		try {
			Files.createDirectories(FILE.getParent());
			Properties props = new Properties();
			props.setProperty(KEY, Float.toString(DarkerSky.getDarkness()));
			try (OutputStream out = Files.newOutputStream(FILE)) {
				props.store(out, "Darker Sky - darkness: 0.0=black .. 1.0=vanilla");
			}
		} catch (Exception e) {
			DarkerSky.LOGGER.warn("[Darker Sky] Failed to save config", e);
		}
	}
}
