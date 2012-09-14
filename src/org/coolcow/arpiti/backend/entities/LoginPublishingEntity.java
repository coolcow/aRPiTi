/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jruiz
 */
public class LoginPublishingEntity extends AbstractEntity {
    
    private String unknownValue1;
    private int unknownValue2;
    private String playerName;
    private String skinName;
    
    public LoginPublishingEntity(final String content) {
        super(content);
        final Matcher matcher = Pattern.compile("(.) (.-.-.):(\\d+) \\((.*)\\) REMOTE Type: (.*)").matcher(content);
        if (matcher.matches()) {
            unknownValue1 = matcher.group(1);
            try {
                unknownValue2 = Integer.parseInt(matcher.group(3));
            } catch (final NumberFormatException ex) {
                unknownValue2 = -1;
            }
            playerName = matcher.group(4);
            skinName = matcher.group(5);
        }        
    }

    public String getUnknownValue1() {
        return unknownValue1;
    }

    public void setUnknownValue1(String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    public void setUnknownValue2(int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

}
