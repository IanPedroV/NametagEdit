package com.nametagedit.plugin.api.data;

import com.nametagedit.plugin.utils.Utils;
import lombok.Data;

import java.util.ArrayList;

/**
 * This class represents a Scoreboard Team. It is used
 * to keep track of the current members of a Team, and
 * is responsible for
 */
@Data
public class FakeTeam {

    // Because some networks use NametagEdit on multiple servers, we may have clashes
    // with the same Team names. The UNIQUE_ID ensures there will be no clashing.
    private static final String UNIQUE_ID = Utils.generateUUID();
    // This represents the number of FakeTeams that have been created.
    // It is used to generate a unique Team name.
    private static int ID = 0;
    private final ArrayList<String> members = new ArrayList<>();
    private String name;
    private String prefix;
    private String suffix;
    private String nameTagVisibility;

    public FakeTeam(String prefix, String suffix, String nameTagVisibility, int sortPriority, boolean playerTag) {
        this.name = UNIQUE_ID + "_" + getNameFromInput(sortPriority) + ++ID + (playerTag ? "+P" : "");
        // It is possible the names of the Team exceeded the length of 16 in the past,
        // and caused crashes as a result. This is a layer of protection against that.
        this.name = this.name.length() > 16 ? this.name.substring(0, 16) : this.name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.nameTagVisibility = nameTagVisibility;
    }

    public void addMember(String player) {
        if (!members.contains(player)) {
            members.add(player);
        }
    }

    public boolean isSimilar(String prefix, String suffix) {
        return this.prefix.equals(prefix) && this.suffix.equals(suffix);
    }

    /**
     * This is a special method to sort nametags in
     * the tablist. It takes a priority and converts
     * it to an alphabetic representation to force a
     * specific sort.
     *
     * @param input the sort priority
     * @return the team name
     */
    private String getNameFromInput(int input) {
        if (input < 0) return "Z";
        char letter = (char) ((input / 5) + 65);
        int repeat = input % 5 + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            builder.append(letter);
        }
        return builder.toString();
    }

    public static String getUniqueId() {
        return UNIQUE_ID;
    }

    public static int getID() {
        return ID;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getNameTagVisibility() {
        return nameTagVisibility;
    }

    public void setNameTagVisibility(String nameTagVisibility) {
        this.nameTagVisibility = nameTagVisibility;
    }
}
