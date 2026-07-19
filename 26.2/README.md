# Darker Sky (26.2)

A client-side Fabric mod that darkens the sky. Targets Minecraft **26.2**.

## How it works

The sky colors are darkened in Java (via Mixins). In vanilla 26.x the sky is made up of two colors:

| Target | What is darkened | Mixin |
|---|---|---|
| Sky dome (upper sky) | the `skyColor` argument of `SkyRenderer#renderSkyDisc(int skyColor)` | `SkyRendererMixin` |
| Bright horizon band / distant haze | the fog color from `FogRenderer#computeFogColor(...)` | `FogRendererMixin` |

The key insight is that the **fog color is also the framebuffer clear color** (`LevelRenderer`
clears with `ARGB.colorFromFloat(0, fogColor.x, fogColor.y, fogColor.z)`). The sky dome disc only
covers down to just above the horizon; the bright band you see below it is the **clear color
(= fog color)**. That cannot be changed from a shader, so darkening the result of `computeFogColor`
in Java darkens the clear color, the `FogColor` uniform, and terrain haze **all at once**.

- The sky dome and the fog color are darkened by the same factor, so the sky is uniformly darker from zenith to horizon.
- Nearby blocks, entities, and light sources (torches, etc.) are not affected.
- Stars, the sun, and the moon are drawn separately and are not darkened, so they become relatively more visible.
- The relevant methods in `SkyRenderer` / `FogRenderer` / `LevelRenderer` have identical signatures in 26.1.x and 26.2, so the Mixins are shared between both versions.

> Note: `computeFogColor` is also used to compute the fog color underwater and in the Nether/End,
> so distant haze in those dimensions is slightly darkened too (a side effect of darkening the Overworld sky).

## Adjusting the darkness (in-game)

Type **`/darkersky`** in chat to open the settings screen. Use the slider to set the
"Sky Brightness" from 0% to 100%; changes are applied to the sky **instantly** as you drag.
When you close the screen the value is saved to a config file and restored on the next launch.

- The command does not use Fabric API; `ChatScreenMixin` intercepts chat input to implement it (**Fabric API not required**).
- Config file: `<game directory>/config/darker-sky.properties` (a single line, `darkness=0.5`)
- Factor meaning: `1.0` = vanilla / `0.5` = half brightness / `0.0` = fully black
- The default is `DarkerSky.DEFAULT_DARKNESS` (`0.5`).

The Mixins read `DarkerSky.getDarkness()` every frame at runtime, so slider changes are reflected in the sky immediately.

### Mod Menu

If [Mod Menu](https://modrinth.com/mod/modmenu) is installed, click the config button on the
**Darker Sky** entry in the mod list to open the same settings screen (closing it returns you to
the mod list). This integration is optional: Mod Menu is a `compileOnly` dependency and the
`modmenu` entrypoint is only invoked when Mod Menu is present, so the mod works fine without it.

Tested with Mod Menu **20.0.1** for Minecraft 26.2.

## Building

```
./gradlew build
```

Output: `build/libs/darker-sky-<version>.jar`
Drop it into the `mods` folder of an installation that has Fabric Loader. **Fabric API is not required.**

## Running in a dev environment

```
./gradlew runClient
```

## License

CC0-1.0
