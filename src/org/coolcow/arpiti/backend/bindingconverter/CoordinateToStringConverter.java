/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.bindingconverter;

import org.coolcow.arpiti.backend.Coordinate;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class CoordinateToStringConverter extends Converter<Coordinate, String> {

    @Override
    public String convertForward(final Coordinate value) {
        return Coordinate.toString(value);
    }

    @Override
    public Coordinate convertReverse(final String value) {
        return Coordinate.parseCoordinate(value);
    }
}