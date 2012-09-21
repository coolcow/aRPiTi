/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.entities.Player;

/**
 *
 * @author jruiz
 */
public class DisconnectStartIRptLine extends AbstractRptLine {
    
    private static final Logger LOG = Logger.getLogger(DisconnectStartIRptLine.class);
    
    private boolean typeA = false;
    private boolean typeB = false;
    private boolean typeC = false;
    private boolean typeD = false;

    private String playerIdentifier;
    private String playerName;
    private String unknownValue1;
    private int unknownValue2;
    private String playerName2;
    private String unknownValue3;
    private int unknownValue4;
    private String playerSkin;

    public DisconnectStartIRptLine() {
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

    public String getPlayerName2() {
        return playerName2;
    }

    public void setPlayerName2(final String playerName2) {
        this.playerName2 = playerName2;
    }

    public String getUnknownValue3() {
        return unknownValue3;
    }

    public void setUnknownValue3(final String unknownValue3) {
        this.unknownValue3 = unknownValue3;
    }

    public int getUnknownValue4() {
        return unknownValue4;
    }

    public void setUnknownValue4(final int unknownValue4) {
        this.unknownValue4 = unknownValue4;
    }

    public String getPlayerSkin() {
        return playerSkin;
    }

    public void setPlayerSkin(final String playerSkin) {
        this.playerSkin = playerSkin;
    }

    public boolean isTypeA() {
        return typeA;
    }

    public void setTypeA(final boolean typeA) {
        this.typeA = typeA;
    }

    public boolean isTypeB() {
        return typeB;
    }

    public void setTypeB(final boolean typeB) {
        this.typeB = typeB;
    }

    public boolean isTypeC() {
        return typeC;
    }

    public void setTypeC(final boolean typeC) {
        this.typeC = typeC;
    }

    public boolean isTypeD() {
        return typeD;
    }

    public void setTypeD(final boolean typeD) {
        this.typeD = typeD;
    }
    
}
