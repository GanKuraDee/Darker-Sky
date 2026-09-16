package com.deeply.ds.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.network.protocol.game.ClientboundCommandsPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * {@code /darkersky} をクライアント側のコマンド辞書に登録し、チャットの
 * コマンド予測候補・シンタックスハイライトに表示されるようにします。
 *
 * <p>サーバーが送ってくるコマンドツリー（{@code handleCommands}）でコマンド辞書が
 * 再構築されるたびに、末尾でこのノードを追加します。ノードはあくまで表示用で、
 * 実行は {@link ChatScreenMixin} が横取りして設定画面を開くため、サーバーへは
 * 送信されません。</p>
 */
@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
	@Shadow
	private CommandDispatcher<ClientSuggestionProvider> commands;

	@Inject(method = "handleCommands", at = @At("TAIL"))
	private void darkerSky$registerCommandSuggestion(ClientboundCommandsPacket packet, CallbackInfo ci) {
		this.commands.register(
			LiteralArgumentBuilder.<ClientSuggestionProvider>literal("darkersky")
				.executes(context -> 0));
	}
}
