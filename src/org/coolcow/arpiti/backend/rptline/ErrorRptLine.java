/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import org.apache.log4j.Logger;

/**
 *
 * @author jruiz
 */
public class ErrorRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(ErrorRptLine.class);
    
    private String message = null;
    
    public ErrorRptLine() {
        super();
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }
        
        setMessage(getRawContent());
        
        return true;
    }
}
