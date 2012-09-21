/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class ErrorRptLine extends AbstractRptLine {

    private String message = null;
    
    public ErrorRptLine() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
