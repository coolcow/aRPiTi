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
public class PDeathRptLine extends AbstractRptLine {

    private int playerId;
    
    public PDeathRptLine() {
        super();
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
        final Matcher matcher = Pattern.compile("^Player Died (\\d+)$").matcher(rawContent);
        if (matcher.matches()) {
            try {
                setPlayerId(Integer.parseInt(matcher.group(1)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            return true;
        } else {
            return false;
        }
    }
}
