/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.coolcow.arpiti.backend.Coordinate;

/**
 *
 * @author jruiz
 */
public class SecondHandZombieInitializedRptLine extends AbstractRptLine {

    private Coordinate coordinate;
    private String identifier;
    private boolean unknownValue;
            
    public SecondHandZombieInitializedRptLine() {
        super();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getIdentifier() {
        return identifier;
    }

    protected void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isUnknownValue() {
        return unknownValue;
    }

    protected void setUnknownValue(boolean unknownValue) {
        this.unknownValue = unknownValue;
    }        

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^\\[\\[(.*?),(.*?),(.*?)\\],(.*?),(.*?)\\]$").matcher(rawContent);
        if (matcher.matches()) {
            try {
                final double x = Double.parseDouble(matcher.group(1));
                final double y = Double.parseDouble(matcher.group(2));
                final double z = Double.parseDouble(matcher.group(3));
                setCoordinate(new Coordinate(x, y, z));
            } catch (final NumberFormatException ex) {
                setCoordinate(null);
            }
            setIdentifier(matcher.group(4));
            try {
                setUnknownValue(Boolean.parseBoolean(matcher.group(5)));
            } catch (final NumberFormatException ex) {
                setUnknownValue(false);
            }
            return true;
        } else {
            return false;
        }
    }
}
