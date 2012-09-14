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
public class StartingLoginEntity extends AbstractEntity {
    
    private String unknownValue1;
    private int unknownValue2;
    private int playerId;
    private String playerName;
    
    public StartingLoginEntity(final String content) {
        super(content);
        
        final Matcher matcher = Pattern.compile("\\[\"(\\d+)\",(. .-.-.):(\\d+) \\((.+)\\) REMOTE\\]").matcher(content);
        if (matcher.matches()) {
            try {
                playerId = Integer.parseInt(matcher.group(1));
            } catch (final NumberFormatException ex) {
                playerId = -1;
            }
            unknownValue1 = matcher.group(2);
            try {
                unknownValue2 = Integer.parseInt(matcher.group(3));
            } catch (final NumberFormatException ex) {
                unknownValue2 = -1;
            }
            playerName = matcher.group(4);
        }        
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

}
