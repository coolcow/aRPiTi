/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class StartingLoginRptLine extends AbstractRptLine {

    private String unknownValue1;
    private int unknownValue2;
    private String playerIdentifier;
    private String playerName;

    public StartingLoginRptLine() {
        super();
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
    
    public String getUnknownValue1() {
        return unknownValue1;
    }

    public void setUnknownValue1(final String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    public void setUnknownValue2(final int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

}
