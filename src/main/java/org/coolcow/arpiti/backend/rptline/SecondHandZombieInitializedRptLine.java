/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.Coordinate;
import org.coolcow.arpiti.backend.rptline.interfaces.CoordinateProvider;

/**
 *
 * @author jruiz
 */
public class SecondHandZombieInitializedRptLine extends AbstractRptLine implements CoordinateProvider {

    private static final Logger LOG = Logger.getLogger(SecondHandZombieInitializedRptLine.class);
        
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
            final String xString = matcher.group(1);
            final String yString = matcher.group(2);
            final String zString = matcher.group(3);
            final String identifierString = matcher.group(4);
            final String unknownValueString = matcher.group(5);
            
            try {
                final double x = Double.parseDouble(xString);
                final double y = Double.parseDouble(yString);
                final double z = Double.parseDouble(zString);
                setCoordinate(new Coordinate(x, y, z));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing coordinate. Set to null.", exception);
                setCoordinate(null);
            }
            setIdentifier(identifierString);
            try {
                setUnknownValue(Boolean.parseBoolean(unknownValueString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue. Set to false. The source String was: " + unknownValueString, exception);
                setUnknownValue(false);
            }
            
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Coordinate> getCoordinates() {
        final Collection<Coordinate> coll = new ArrayList<>();
        if (coordinate != null) {
            coll.add(coordinate);
        }
        return coll;
    }
}
