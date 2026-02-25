package com.sydders.chatHeads.listener;

import io.papermc.paper.chat.ChatRenderer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;


public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncChat(AsyncChatEvent event) {
        ChatRenderer existingRenderer = event.renderer();

        event.renderer((source, sourceDisplayName, message, viewer) -> {
            Component original = existingRenderer.render(source, sourceDisplayName, message, viewer);
            Component head;

            try {
                head = MiniMessage.miniMessage().deserialize("<head:" + source.getUniqueId() + ":true> ");
            } catch (Exception e) {
                head = MiniMessage.miniMessage().deserialize("<head:8667ba71-b85a-4004-af54-457a9734eed7:true> ");
            }

            return head.append(original);
        });
    }
}
