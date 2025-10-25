package com.sydders.chatHeads;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.object.ObjectContents;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;

import static org.bukkit.Bukkit.getServer;


public class ChatListener implements Listener {

    private final ChatHeads plugin;

    public ChatListener(ChatHeads plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        Player p = event.getPlayer();
        String msg = PlainTextComponentSerializer.plainText().serialize(event.message());

        event.setCancelled(true);

        final Component component = MiniMessage.miniMessage().deserialize(
                "<head:" + p.getUniqueId() + ":true> <" + p.getName() + "> " + msg
        );

        Bukkit.getScheduler().runTask(plugin, () -> {
            for (Player viewer : Bukkit.getOnlinePlayers()) {
                viewer.sendMessage(component);
            }
        });
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
