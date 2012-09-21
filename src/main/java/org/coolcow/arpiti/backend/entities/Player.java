package org.coolcow.arpiti.backend.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jruiz
 */
public class Player {
    
    private String name = null;;
    private String identifier = null;;
    private int hiveId = -1;
    private String skinName = null;
    
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
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
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
