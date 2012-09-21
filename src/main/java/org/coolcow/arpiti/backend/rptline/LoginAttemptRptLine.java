/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class LoginAttemptRptLine extends AbstractRptLine {

    private String playerIdentifier;
    private String playerName;

    public LoginAttemptRptLine() {
    }

    public String getPlayerIdentifier() {
        return playerIdentifier;
    }

    public void setPlayerIdentifier(final String playerIdentifier) {
        this.playerIdentifier = playerIdentifier;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }
    
}
