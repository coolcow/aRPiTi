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
    private String unknownValue2;
    private int unknownValue3;
    private String playerName;
    private String skinName;
    
    public LoginPublishingEntity(final String content) {
        super(content);
        final Matcher matcher = Pattern.compile("(.) (.-.-.):(.) \\((.*)\\) REMOTE Type: (.*)").matcher(content);
        if (matcher.matches()) {
            unknownValue1 = matcher.group(1);
            unknownValue2 = matcher.group(2);
            try {
                unknownValue3 = Integer.parseInt(matcher.group(3));
            } catch (final NumberFormatException ex) {
                unknownValue3 = -1;
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

    public String getUnknownValue2() {
        return unknownValue2;
    }

    public void setUnknownValue2(String unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public int getUnknownValue3() {
        return unknownValue3;
    }

    public void setUnknownValue3(int unknownValue3) {
        this.unknownValue3 = unknownValue3;
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
