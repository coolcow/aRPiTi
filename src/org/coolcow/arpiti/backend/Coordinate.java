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
        final Matcher matcher = Pattern.compile("^\\[(.*?), (.*?), (.*?)\\]$").matcher(value);
        if (matcher.matches()) {
            return new Coordinate(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)), Double.parseDouble(matcher.group(3)));
        } else {
            return null; // throw exception ?
        }        
    }
}
