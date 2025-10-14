package com.sydders.chatHeads;


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

        Bukkit.getScheduler().runTask(this.plugin, () -> {
            String cmd = "tellraw @a [{\"player\":\"" + p.getName() + "\"}, \" <" + p.getName() + "> " + msg + "\"]";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
        });
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
