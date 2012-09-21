/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class HiveRptLine extends AbstractRptLine {

    private boolean typeWrite = false;    
    
    private String playerIdentifier;
    private int hiveId;
    private int unknownValue1;
    private int unknownValue2;

    public HiveRptLine() {
        super();
    }

    public boolean isTypeWrite() {
        return typeWrite;
    }

    public void setTypeWrite(final boolean typeWrite) {
        this.typeWrite = typeWrite;
    }

    public String getPlayerIdentifier() {
        return playerIdentifier;
    }

    public void setPlayerIdentifier(final String playerIdentifier) {
        this.playerIdentifier = playerIdentifier;
    }

    public int getHiveId() {
        return hiveId;
    }

    public void setHiveId(final int hiveId) {
        this.hiveId = hiveId;
    }
    
    public int getUnknownValue1() {
        return unknownValue1;
    }

    public void setUnknownValue1(final int unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    public void setUnknownValue2(final int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }
        
}
