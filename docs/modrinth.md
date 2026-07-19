# Modrinth listing

Text for the Darker Sky page on Modrinth. Paste **Summary** into the summary
field, and **Description** into the description (body) field.

---

## Summary

> Darkens the daytime sky — both the sky dome and the bright horizon haze — without dimming your world. Adjust it live with a slider via /darkersky or Mod Menu. Client-side, no Fabric API needed.

---

## Description

# Darker Sky

**Darker Sky** is a lightweight, client-side mod that dims the daytime sky for a calmer,
more atmospheric look — **without touching the brightness of the world itself**.

Unlike shader packs, it changes only the **sky's colors**. Your terrain, blocks, entities,
and light sources (torches, lanterns, …) keep their normal brightness. Stars, the sun, and
the moon stay bright, so a dimmed sky actually makes them stand out more.

<!-- Tip: add before/after screenshots here -->

## ✨ Features

- **Darkens the whole sky** — both the upper **sky dome** and the bright band along the
  **horizon** (the atmospheric fog), so the sky dims evenly from zenith to horizon.
- **Adjust it in-game, live** — type **`/darkersky`** to open a settings screen with a
  slider. Changes apply **instantly** as you drag.
- **Saved automatically** — your setting is written to a config file and restored on the
  next launch.
- **Mod Menu support** — if [Mod Menu](https://modrinth.com/mod/modmenu) is installed,
  click the config button on the Darker Sky entry to open the same screen.
- **Lightweight & compatible** — client-side only, **no Fabric API required**. Purely
  visual: it does not affect gameplay, terrain, or block lighting.
- **English & 日本語** language support.

## 🎛️ How to use

1. Install **Fabric Loader** and drop Darker Sky into your `mods` folder.
2. In game, type **`/darkersky`** in chat — or open the config from **Mod Menu**.
3. Drag the **Sky Brightness** slider: `0%` = fully black, `100%` = vanilla.
4. Close the screen — your value is saved.

You can also edit it by hand in `config/darker-sky.properties` (`darkness=0.5`).
The default is `0.5` (half brightness).

## 🧩 Requirements

| | |
|---|---|
| **Mod loader** | Fabric |
| **Minecraft** | 26.1.x *(use the `26.1.x` build)* · 26.2 *(use the `26.2` build)* |
| **Fabric API** | Not required |
| **Mod Menu** | Optional — only for the config button in the mod list |

## ℹ️ Notes

- The sky and the atmospheric fog share the same color, so distant fog/haze (including
  underwater and the Nether/End) is dimmed a little too. Nearby terrain is unaffected.
- Multiplayer-friendly: it's a purely visual, client-side mod.

## 🔗 Links

- Source & issues: https://github.com/GanKuraDee/Darker-Sky

---

## 日本語

**Darker Sky** は、日中の空を暗くして落ち着いた雰囲気にする軽量なクライアント Mod です。
**ワールド自体の明るさは変えません。**

シェーダーパックと違い、変更するのは**空の色だけ**。地形・ブロック・エンティティ・
松明などの光源の明るさはそのままです。星・太陽・月も暗くならないので、暗い空で
かえってはっきり見えます。

### 特徴
- **空全体を暗く** — 空ドームだけでなく、地平線の明るい帯（大気フォグ）も暗くするので、
  天頂から地平線まで均一に暗くなります。
- **ゲーム内で即調整** — `/darkersky` で設定画面（スライダー）を開けます。ドラッグ中に
  即反映され、自動保存されます。
- **Mod Menu 対応** — Mod Menu 導入時は、Mod 一覧の設定ボタンからも開けます。
- **軽量・高互換** — クライアント専用、**Fabric API 不要**。

### 使い方
1. Fabric Loader を導入し、`mods` フォルダに入れる
2. `/darkersky` を入力、または Mod Menu の設定ボタン
3. 「空の明るさ」スライダーを調整（`0%`=真っ暗 / `100%`=バニラ）
4. 閉じると保存されます（`config/darker-sky.properties`、初期値 `0.5`）

### 対応環境
- **Minecraft 26.1.x**（`26.1.x` ビルド）/ **26.2**（`26.2` ビルド）、Fabric Loader
- Fabric API 不要 / Mod Menu 任意
