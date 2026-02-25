package com.sydders.chatHeads;

import com.sydders.chatHeads.listener.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatHeads extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Chat Heads enabled!");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Chat Heads disabled!");
    }
}
