package io.ekmot276;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

@Plugin(id = "velocitychatbridge", name = "VelocityChatBridge", version = "0.1.0-SNAPSHOT",
		url = "https://www.ekmot276.io", description = "Simple plugin to parse messages to all servers under a velocity proxy", authors = "Ekmot276")

public class VelocityChatBridge {

	private final ProxyServer server;
	private final Logger logger;

	@Inject
	public VelocityChatBridge(ProxyServer server, Logger logger) {
		this.server = server;
		this.logger = logger;

		logger.info("VelocityChatBridge 0.1.0-SNAPSHOT has been successfully enabled!");
	}

	@Subscribe
	public void onPlayerChat(PlayerChatEvent event) {

		final String message = event.getMessage();
		final Player sender = event.getPlayer();
		String username = sender.getUsername();

		server.sendMessage(Component.text("<" + username + "> " + message));
		event.setResult(PlayerChatEvent.ChatResult.denied());

	}

}
