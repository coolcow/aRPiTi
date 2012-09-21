/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class PDeathRptLine extends AbstractRptLine {

    private String playerIdentifier;
    
    public PDeathRptLine() {
        super();
    }

    public String getPlayerIdentifier() {
        return playerIdentifier;
    }

    public void setPlayerIdentifier(final String playerIdentifier) {
        this.playerIdentifier = playerIdentifier;
    }

}
