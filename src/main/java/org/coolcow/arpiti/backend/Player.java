package org.coolcow.arpiti.backend;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jruiz
 */
public class Player {
    
    private String name;
    private String identifier;
    private int serverId;
    private int hiveId;
    private String skinName;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getHiveId() {
        return hiveId;
    }

    public void setHiveId(int hiveId) {
        this.hiveId = hiveId;
    }        

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }
        
    @Override
    public String toString() {
        return toString(this);
    }
    
    public static String toString(final Player player) {
        return player.name + " (" + player.identifier + ")";
    }
    
    public void merge(final Player player) {
        if (player != null) {
            if (getHiveId() < 0) {
                setHiveId(player.getHiveId());
            }            
            if (getIdentifier() == null) {
                setIdentifier(player.getIdentifier());
            }
            if (getName() == null) {
                setName(player.getName());
            }
            if (getSkinName() == null) {
                setSkinName(player.getSkinName());
            }            
        }
    }
}
