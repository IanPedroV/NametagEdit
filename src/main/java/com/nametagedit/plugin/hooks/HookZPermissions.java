package com.nametagedit.plugin.hooks;

import com.nametagedit.plugin.NametagHandler;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.tyrannyofheaven.bukkit.zPermissions.ZPermissionsPlayerUpdateEvent;

public class HookZPermissions implements Listener {

    private NametagHandler handler;

    public HookZPermissions(NametagHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onZPermissionsRankChangeEvent(ZPermissionsPlayerUpdateEvent event) {
        handler.applyTagToPlayer(event.getPlayer());
    }

}