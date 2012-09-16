/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author jruiz
 */
public class LoginAttemptRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(LoginAttemptRptLine.class);
    
    private String playerName;
    private int playerId;

    public LoginAttemptRptLine() {
    }

    public String getPlayerName() {
        return playerName;
    }

    protected void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    protected void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("\"(.*)\" (.*)").matcher(rawContent);
        
        if (matcher.matches()) {
            final String playerIdString = matcher.group(1);
            final String playerNameString = matcher.group(2);
            
            try {
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(playerId = -1);
            }
            setPlayerName(playerNameString);
            
            return true;
        } else {
            return false;
        }
    }
}
