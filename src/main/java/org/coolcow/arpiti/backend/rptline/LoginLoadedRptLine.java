/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class LoginLoadedRptLine extends AbstractRptLine {

    private String playerName;
    private String skinName;
    private String unknownValue1;
    private int unknownValue2;

    public LoginLoadedRptLine() {
        super();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }
    
    public String getSkiinName() {
        return skinName;
    }

    public void setSkinName(final String skinName) {
        this.skinName = skinName;
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
