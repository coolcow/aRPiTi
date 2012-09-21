/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.entities;

/**
 *
 * @author jruiz
 */
public class Item {

    private String type;
    private int rounds;

    public Item(String type) {
        this.type = type;
    }

    public Item(String type, int rounds) {
        this(type);
        this.rounds = rounds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return (rounds <= 0)? getType() : getType() + " (" + Integer.toString(rounds) + ")";
    }

}    