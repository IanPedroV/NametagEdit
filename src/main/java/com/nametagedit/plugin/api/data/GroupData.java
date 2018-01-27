package com.nametagedit.plugin.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * This class represents a group nametag. There
 * are several properties available.
 */
@Data
@AllArgsConstructor
public class GroupData implements INametag {

    private String groupName;
    private String prefix;
    private String suffix;
    private String permission;
    private Permission bukkitPermission;
    private int sortPriority;


    public GroupData(String groupName, String prefix, String suffix, String permission, Permission bukkitPermission, int sortPriority) {
        this.groupName = groupName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.permission = permission;
        this.bukkitPermission = bukkitPermission;
        this.sortPriority = sortPriority;
    }

    public GroupData() {

    }

    public void setPermission(String permission) {
        this.permission = permission;
        bukkitPermission = new Permission(permission, PermissionDefault.FALSE);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPermission() {
        return permission;
    }

    public Permission getBukkitPermission() {
        return bukkitPermission;
    }

    public void setBukkitPermission(Permission bukkitPermission) {
        this.bukkitPermission = bukkitPermission;
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

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public int getSortPriority() {
        return this.sortPriority;
    }

    public void setSortPriority(int sortPriority) {
        this.sortPriority = sortPriority;
    }

    @Override
    public boolean isPlayerTag() {
        return isPlayerTag();
    }

}