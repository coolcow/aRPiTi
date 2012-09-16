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
public class LoginPublishingRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(LoginPublishingRptLine.class);
    
    private String unknownValue1;
    private int unknownValue2;
    private String playerName;
    private String skinName;

    public LoginPublishingRptLine() {
        super();
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

    public String getSkinName() {
        return skinName;
    }

    protected void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^(.) (.-.-.):(\\d+) \\((.*)\\) REMOTE Type: (.*)$").matcher(rawContent);
        
        if (matcher.matches()) {
            final String unknownValue1String = matcher.group(1);
            final String unknownValue2String = matcher.group(3);
            final String playerNameString = matcher.group(4);
            final String skinNameString = matcher.group(5);
            
            
            setUnknownValue1(unknownValue1String);
            try {
                setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                setUnknownValue2(-1);
            }
            setPlayerName(playerNameString);
            setSkinName(skinNameString);
            
            return true;
        } else {
            return false;
        }
    }
}
