package com.nametagedit.plugin.hooks;

import com.nametagedit.plugin.NametagHandler;
import lombok.AllArgsConstructor;
import org.anjocaido.groupmanager.events.GMUserEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class HookGroupManager implements Listener {

    private NametagHandler handler;

    public HookGroupManager(NametagHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onGMUserEvent(GMUserEvent event) {
        Player player = event.getUser().getBukkitPlayer();
        if (player != null) {
            handler.applyTagToPlayer(player);
        }
    }

}