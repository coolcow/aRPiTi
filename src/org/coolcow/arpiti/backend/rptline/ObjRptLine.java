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
public class ObjRptLine extends AbstractRptLine {

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
            try {
                setObjectId(Integer.parseInt(matcher.group(1)));
            } catch (final NumberFormatException ex) {
                setObjectId(-1);
            }
            setObjectName(matcher.group(2));
            return true;
        } else {
            return false;
        }
    }
}
