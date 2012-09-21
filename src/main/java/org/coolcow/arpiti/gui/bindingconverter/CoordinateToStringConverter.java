/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.bindingconverter;

import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.entities.Coordinate;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class CoordinateToStringConverter extends Converter<Coordinate, String> {

    private static final Logger LOG = Logger.getLogger(CoordinateToStringConverter.class);
    
    @Override
    public String convertForward(final Coordinate value) {
        return Coordinate.toString(value);
    }

    @Override
    public Coordinate convertReverse(final String value) {
        try {
            return Coordinate.parseCoordinate(value);
        } catch (final NumberFormatException exception) {
            LOG.warn("The given String could not be parsed to a Coordinate: " + value, exception);
            throw exception;
        }
    }
}