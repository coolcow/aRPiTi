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
public class DisconnectStartIRptLine extends AbstractRptLine {
    
    private static final Logger LOG = Logger.getLogger(DisconnectStartIRptLine.class);
    
    private boolean typeA = false;
    private boolean typeB = false;
    private boolean typeC = false;
    private boolean typeD = false;

    private String playerName;
    private int playerId;
    private String unknownValue1;
    private int unknownValue2;
    private String playerName2;
    private int unknownValue3;
    private int unknownValue4;
    private String playerSkin;

    public DisconnectStartIRptLine() {
        super();
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

    public String getUnknownValue1() {
        return unknownValue1;
    }

    protected void setUnknownValue1(String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    protected void setUnknownValue2(int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public String getPlayerName2() {
        return playerName2;
    }

    protected void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }

    public int getUnknownValue3() {
        return unknownValue3;
    }

    protected void setUnknownValue3(int unknownValue3) {
        this.unknownValue3 = unknownValue3;
    }

    public int getUnknownValue4() {
        return unknownValue4;
    }

    protected void setUnknownValue4(int unknownValue4) {
        this.unknownValue4 = unknownValue4;
    }

    public String getPlayerSkin() {
        return playerSkin;
    }

    protected void setPlayerSkin(String playerSkin) {
        this.playerSkin = playerSkin;
    }

    public boolean isTypeA() {
        return typeA;
    }

    protected void setTypeA(boolean typeA) {
        this.typeA = typeA;
    }

    public boolean isTypeB() {
        return typeB;
    }

    protected void setTypeB(boolean typeB) {
        this.typeB = typeB;
    }

    public boolean isTypeC() {
        return typeC;
    }

    protected void setTypeC(boolean typeC) {
        this.typeC = typeC;
    }

    public boolean isTypeD() {
        return typeD;
    }

    protected void setTypeD(boolean typeD) {
        this.typeD = typeD;
    }
    
    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcherA = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: (. .-.-.):(\\d+) \\((.+)\\)( REMOTE)?$").matcher(rawContent);
        final Matcher matcherB = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: (. .-.-.):(\\d+)( REMOTE)?$").matcher(rawContent);
        final Matcher matcherC = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: <NULL-object>$").matcher(rawContent);
        final Matcher matcherD = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: (\\w+)# (\\d+): (.+) REMOTE?$").matcher(rawContent);

        if (matcherA.matches()) {
            setTypeA(true);
            
            final String playerNameString = matcherA.group(1);
            final String playerIdString = matcherA.group(2);
            final String unknownValue1String = matcherA.group(3);
            final String unknownValue2String = matcherA.group(4);
            final String playerName2String = matcherA.group(5);            
            
            setPlayerName(playerNameString);
            try {                 
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(-1);
            }
            setUnknownValue1(unknownValue1String);
            try {
                setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                setUnknownValue2(-1);
            }
            setPlayerName2(playerName2String);
            
            return true;
        } else if (matcherB.matches()) {
            setTypeB(true);
            
            final String playerNameString = matcherB.group(1);
            final String playerIdString = matcherB.group(2);
            final String unknownValue1String = matcherB.group(3);
            final String unknownValue2String = matcherB.group(4);
            
            setPlayerName(playerNameString);
            try {
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(-1);
            }
            setUnknownValue1(unknownValue1String);
            try {
                setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                setUnknownValue2(-1);
            }
            
            return true;
        } else if (matcherC.matches()) {
            setTypeC(true);            
            
            final String playerNameString = matcherC.group(1);
            final String playerIdString = matcherC.group(2);
            
            setPlayerName(playerNameString);
            try {
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(-1);
            }
            setUnknownValue1(null);
            setUnknownValue2(-1);
            return true;
        } else if (matcherD.matches()) {
            setTypeD(true);

            final String playerNameString = matcherD.group(1);
            final String playerIdString = matcherD.group(2);
            final String unknownValue3String = matcherD.group(3);
            final String unknownValue4String = matcherD.group(4);
            final String playerSkinString = matcherD.group(5);
            
            setPlayerName(playerNameString);
            try {
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(-1);
            }
            try {
                setUnknownValue3(Integer.parseInt(unknownValue3String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue3. Set to -1. The source String was: " + unknownValue3String, exception);
                setUnknownValue3(-1);
            }
            try {
                setUnknownValue4(Integer.parseInt(unknownValue4String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue4. Set to -1. The source String was: " + unknownValue4String, exception);
                setUnknownValue4(-1);
            }
            setPlayerSkin(playerSkinString);
            
            return true;
        } else {
            return false;
        }
    }
}
