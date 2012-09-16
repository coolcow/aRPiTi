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
public class WriteRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(WriteRptLine.class);
    
    private String unknownValue;
    
    public WriteRptLine() {
        super();
    }

    public String getUnknownValue() {
        return unknownValue;
    }

    protected void setUnknownValue(String unknownValue) {
        this.unknownValue = unknownValue;
    }
    
    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^\\[\\'(\\w+)\\'\\]$").matcher(rawContent);
        
        if (matcher.matches()) {
            final String unknownValueString = matcher.group(1);
            
            setUnknownValue(unknownValueString);
            
            return true;
        } else {
            return false;
        }
    }
}
