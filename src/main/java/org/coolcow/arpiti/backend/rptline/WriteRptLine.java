/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class WriteRptLine extends AbstractRptLine {

    private String unknownValue;
    
    public WriteRptLine() {
        super();
    }

    public String getUnknownValue() {
        return unknownValue;
    }

    public void setUnknownValue(String unknownValue) {
        this.unknownValue = unknownValue;
    }
}
