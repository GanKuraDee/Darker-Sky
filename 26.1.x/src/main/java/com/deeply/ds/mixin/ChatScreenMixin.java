package com.deeply.ds.mixin;

import com.deeply.ds.gui.DarkerSkyScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * チャットで {@code /darkersky} と入力されたら設定画面を開き、サーバーへは送信しません。
 *
 * <p>fabric-api を使わないクライアントコマンド実装です。{@code handleChatInput} は
 * 入力をサーバーへ送信するメソッドで、ここを HEAD でキャンセルすることで
 * 「未知のコマンド」エラーを防ぎます。チャット画面自体は呼び出し元
 * ({@code keyPressed}) が閉じるため、次のフレームで設定画面を開きます。</p>
 */
@Mixin(ChatScreen.class)
public class ChatScreenMixin {
	@Inject(method = "handleChatInput", at = @At("HEAD"), cancellable = true)
	private void darkerSky$openSettings(String message, boolean addToRecent, CallbackInfo ci) {
		if (message != null && message.strip().equalsIgnoreCase("/darkersky")) {
			Minecraft client = Minecraft.getInstance();
			// チャット画面が閉じた後に開くため次フレームへ遅延。
			// setScreen は通常の描画ループで反映されるため、
			// setScreenAndShow の強制描画による黒い画面のちらつきが起きない。
			client.execute(() -> client.setScreen(new DarkerSkyScreen(null)));
			ci.cancel();
		}
	}
}
