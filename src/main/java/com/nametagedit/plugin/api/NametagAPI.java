package com.nametagedit.plugin.api;

import com.nametagedit.plugin.NametagHandler;
import com.nametagedit.plugin.NametagManager;
import com.nametagedit.plugin.api.data.FakeTeam;
import com.nametagedit.plugin.api.data.Nametag;
import com.nametagedit.plugin.api.events.NametagEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Implements the INametagAPI interface. There only
 * exists one instance of this class.
 */
public final class NametagAPI implements INametagApi {

    private NametagHandler handler;
    private NametagManager manager;

    public NametagAPI(NametagHandler handler, NametagManager manager) {
        this.handler = handler;
        this.manager = manager;
    }

    @Override
    public Nametag getNametag(Player player) {
        FakeTeam team = manager.getFakeTeam(player.getName());
        boolean nullTeam = team == null;
        return new Nametag(nullTeam ? "" : team.getPrefix(), nullTeam ? "" : team.getSuffix(), nullTeam ? "always" : team.getNameTagVisibility());
    }

    @Override
    public void clearNametag(Player player) {
        if (shouldFireEvent(player, NametagEvent.ChangeType.CLEAR)) {
            manager.reset(player.getName());
        }
    }

    @Override
    public void reloadNametag(Player player) {
        if (shouldFireEvent(player, NametagEvent.ChangeType.RELOAD)) {
            handler.applyTagToPlayer(player);
        }
    }

    @Override
    public void clearNametag(String player) {
        manager.reset(player);
    }

    @Override
    public void setPrefix(Player player, String prefix, String nameTagVisibility) {
        FakeTeam fakeTeam = manager.getFakeTeam(player.getName());
        setNametagAlt(player, prefix, fakeTeam == null ? null : fakeTeam.getSuffix(), nameTagVisibility);
    }

    @Override
    public void setSuffix(Player player, String suffix, String nameTagVisibility) {
        FakeTeam fakeTeam = manager.getFakeTeam(player.getName());
        setNametagAlt(player, fakeTeam == null ? null : fakeTeam.getPrefix(), suffix, nameTagVisibility);
    }

    @Override
    public void setPrefix(String player, String prefix, String nameTagVisibility) {
        FakeTeam fakeTeam = manager.getFakeTeam(player);
        manager.setNametag(player, prefix, fakeTeam == null ? null : fakeTeam.getSuffix(), fakeTeam == null ? "always" : fakeTeam.getNameTagVisibility());
    }

    @Override
    public void setSuffix(String player, String suffix, String nameTagVisibility) {
        FakeTeam fakeTeam = manager.getFakeTeam(player);
        manager.setNametag(player, fakeTeam == null ? null : fakeTeam.getPrefix(), suffix, fakeTeam == null ? "always" : fakeTeam.getNameTagVisibility());
    }

    @Override
    public void setNametag(Player player, String prefix, String suffix, String nameTagVisibility) {
        setNametagAlt(player, prefix, suffix, nameTagVisibility);
    }

    @Override
    public void setNametag(String player, String prefix, String suffix, String nameTagVisibility) {
        manager.setNametag(player, prefix, suffix, nameTagVisibility);
    }

    /**
     * Private helper function to reduce redundancy
     */
    private boolean shouldFireEvent(Player player, NametagEvent.ChangeType type) {
        NametagEvent event = new NametagEvent(player.getName(), "", getNametag(player), type);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    /**
     * Private helper function to reduce redundancy
     */
    private void setNametagAlt(Player player, String prefix, String suffix, String nameTagVisibility) {
        Nametag nametag = new Nametag(handler.formatWithPlaceholders(player, prefix),
                handler.formatWithPlaceholders(player, suffix), nameTagVisibility);

        NametagEvent event = new NametagEvent(player.getName(), prefix, nametag, NametagEvent.ChangeType.UNKNOWN);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        manager.setNametag(player.getName(), nametag.getPrefix(), nametag.getSuffix(), nametag.getNameTagVisibility());
    }

}