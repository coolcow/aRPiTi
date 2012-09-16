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
public class ObjRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(ObjRptLine.class);
    
    private int objectId;
    private String objectName;

    public ObjRptLine() {
        super();
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    protected void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    
    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^\"(\\d+)\"(.*)$").matcher(rawContent);
        
        if (matcher.matches()) {
            final String objecIdString = matcher.group(1);
            final String objecNameString = matcher.group(2);
            
            try {
                setObjectId(Integer.parseInt(objecIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing objecId. Set to -1. The source String was: " + objecIdString, exception);
                setObjectId(-1);
            }
            setObjectName(objecNameString);
            
            return true;
        } else {
            return false;
        }
    }
}
