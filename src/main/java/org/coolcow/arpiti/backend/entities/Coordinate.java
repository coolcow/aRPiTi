/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author jruiz
 */
public class Coordinate {
    
    private static final Logger LOG = Logger.getLogger(Coordinate.class);
    
    private final double x;
    private final double y;
    private final double z;
    
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public double getGpsX() {
        return x;
    }
    
    public double getGpsY() {
        return 15350 - y;
    }
    
    public int getGpsZ() {
        return (int) (z * 10);
    }

    @Override
    public String toString() {
        return toString(this);
    }
    
    public static String toString(final Coordinate coordinate) {
        return "[" + Double.toString(coordinate.getX()) + "," + Double.toString(coordinate.getY()) + "," + Double.toString(coordinate.getZ()) + " ]";
    }

    public static Coordinate parseCoordinate(final String value) {
        LOG.fatal(value);
        final String floatingPointRegex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
        final Matcher matcher = Pattern.compile("^\\[(" + floatingPointRegex + "),(" + floatingPointRegex + "),(" + floatingPointRegex + ")\\]$").matcher(value);
        
        if (matcher.matches()) {
            final Double x = Double.parseDouble(matcher.group(1));
            final Double y = Double.parseDouble(matcher.group(3));
            final Double z = Double.parseDouble(matcher.group(5));
            return new Coordinate(x, y, z);
        } else {            
            throw new IllegalArgumentException("The given String does not match with the coordinate regex. " + value);
        }        
    }
}
