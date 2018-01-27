package com.nametagedit.plugin.hooks;

import com.nametagedit.plugin.NametagEdit;
import lombok.AllArgsConstructor;
import me.libraryaddict.disguise.events.DisguiseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class HookLibsDisguise implements Listener {

    private NametagEdit plugin;

    public HookLibsDisguise(NametagEdit plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDisguiseEvent(final DisguiseEvent event) {
        if (event.getEntity() instanceof Player) {
            plugin.getHandler().getNametagManager().reset(event.getEntity().getName());
            new BukkitRunnable() {
                @Override
                public void run() {
                    plugin.getHandler().applyTagToPlayer((Player) event.getEntity());
                }
            }.runTaskLater(plugin, 3);
        }
    }

}