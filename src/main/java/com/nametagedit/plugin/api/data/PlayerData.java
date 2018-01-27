package com.nametagedit.plugin.api.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.UUID;

/**
 * This class represents a player nametag. There
 * are several properties available.
 */
@Getter
@Setter
@AllArgsConstructor
public class PlayerData implements INametag {

    private String name;
    private UUID uuid;
    private String prefix;
    private String suffix;
    private int sortPriority;

    public PlayerData() {

    }

    public PlayerData(String name, UUID uuid, String prefix, String suffix, int sortPriority) {
        this.name = name;
        this.uuid = uuid;
        this.prefix = prefix;
        this.suffix = suffix;
        this.sortPriority = sortPriority;
    }

    public static PlayerData fromFile(String key, YamlConfiguration file) {
        if (!file.contains("Players." + key)) return null;
        PlayerData data = new PlayerData();
        data.setUuid(UUID.fromString(key));
        data.setName(file.getString("Players." + key + ".Name"));
        data.setPrefix(file.getString("Players." + key + ".Prefix", ""));
        data.setSuffix(file.getString("Players." + key + ".Suffix", ""));
        data.setSortPriority(file.getInt("Players." + key + ".SortPriority", -1));
        return data;
    }

    @Override
    public String getPrefix() {
        return this.getPrefix();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getSuffix() {
        return this.getSuffix();
    }

    @Override
    public int getSortPriority() {
        return this.sortPriority;
    }

    @Override
    public boolean isPlayerTag() {
        return isPlayerTag();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public void setSortPriority(int sortPriority) {
        this.sortPriority = sortPriority;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }
}