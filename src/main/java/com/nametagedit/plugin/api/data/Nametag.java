package com.nametagedit.plugin.api.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Nametag {
    private String prefix;
    private String suffix;
    private String nameTagVisibility;


    public Nametag(String prefix, String suffix, String nameTagVisibility) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.nameTagVisibility = nameTagVisibility;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getNameTagVisibility() {
        return nameTagVisibility;
    }

    public void setNameTagVisibility(String nameTagVisibility) {
        this.nameTagVisibility = nameTagVisibility;
    }
}