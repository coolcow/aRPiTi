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
public class LoginAttemptEntity extends AbstractEntity {
    
    private String playerName;
    private int playerId;
    
    public LoginAttemptEntity(final String content) {
        super(content);
        final Matcher matcher = Pattern.compile("\"(.*)\" (.*)").matcher(content);
        if (matcher.matches()) {
            try {
                playerId = Integer.parseInt(matcher.group(1));
            } catch (final NumberFormatException ex) {
                playerId = -1;
            }
            playerName = matcher.group(2);
        }        
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    
}
