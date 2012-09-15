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
public class StartingLoginRptLine extends AbstractRptLine {

    private String unknownValue1;
    private int unknownValue2;
    private int playerId;
    private String playerName;

    public StartingLoginRptLine() {
        super();
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

    public String getPlayerName() {
        return playerName;
    }

    protected void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^\\[\"(\\d+)\",(. .-.-.):(\\d+) \\((.+)\\) REMOTE\\]$").matcher(rawContent);
        if (matcher.matches()) {
            try {
                setPlayerId(Integer.parseInt(matcher.group(1)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            setUnknownValue1(matcher.group(2));
            try {
                setUnknownValue2(Integer.parseInt(matcher.group(3)));
            } catch (final NumberFormatException ex) {
                setUnknownValue2(-1);
            }
            setPlayerName(matcher.group(4));
            return true;
        } else {
            return false;
        }
    }
}
