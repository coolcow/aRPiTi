/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jruiz
 */
public class DisconnectStartIRptLine extends AbstractRptLine {

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
            setPlayerName(matcherA.group(1));
            try {
                setPlayerId(Integer.parseInt(matcherA.group(2)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            setUnknownValue1(matcherA.group(3));
            try {
                setUnknownValue2(Integer.parseInt(matcherA.group(4)));
            } catch (final NumberFormatException ex) {
                setUnknownValue2(-1);
            }
            setPlayerName2(matcherA.group(5));
            return true;
        } else if (matcherB.matches()) {
            setPlayerName(matcherB.group(1));
            try {
                setPlayerId(Integer.parseInt(matcherB.group(2)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            setUnknownValue1(matcherB.group(3));
            try {
                setUnknownValue2(Integer.parseInt(matcherB.group(4)));
            } catch (final NumberFormatException ex) {
                setUnknownValue2(-1);
            }
            return true;
        } else if (matcherC.matches()) {
            setPlayerName(matcherC.group(1));
            try {
                setPlayerId(Integer.parseInt(matcherC.group(2)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            setUnknownValue1(null);
            setUnknownValue2(-1);
            return true;
        } else if (matcherD.matches()) {
            setPlayerName(matcherD.group(1));
            try {
                setPlayerId(Integer.parseInt(matcherD.group(2)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            try {
                setUnknownValue3(Integer.parseInt(matcherD.group(3)));
            } catch (final NumberFormatException ex) {
                setUnknownValue3(-1);
            }
            try {
                setUnknownValue4(Integer.parseInt(matcherD.group(4)));
            } catch (final NumberFormatException ex) {
                setUnknownValue4(-1);
            }
            setPlayerSkin(matcherD.group(5));
            return true;
        } else {
            return false;
        }
    }
}
