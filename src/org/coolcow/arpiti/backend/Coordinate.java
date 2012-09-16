/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jruiz
 */
public class Coordinate {
    
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

    public static String toString(final Coordinate coordinate) {
        return "[" + Double.toString(coordinate.getX()) + ", " + Double.toString(coordinate.getY()) + ", " + Double.toString(coordinate.getZ()) + " ]";
    }

    public static Coordinate parseCoordinate(final String value) {
        final String floatingPointRegex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
        final Matcher matcher = Pattern.compile("^\\[(" + floatingPointRegex + "), (" + floatingPointRegex + "), (" + floatingPointRegex + ")\\]$").matcher(value);
        if (matcher.matches()) {
            return new Coordinate(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(3)), Double.parseDouble(matcher.group(5)));
        } else {            
            throw new IllegalArgumentException("The given String does not match with the coordinate regex.");
        }        
    }
}
