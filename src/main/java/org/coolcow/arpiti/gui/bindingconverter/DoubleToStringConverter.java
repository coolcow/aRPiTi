/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.bindingconverter;

import org.apache.log4j.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class DoubleToStringConverter extends Converter<Double, String> {
    
    private static final Logger LOG = Logger.getLogger(DoubleToStringConverter.class);

    @Override
    public String convertForward(final Double value) {
        return Double.toString(value);
    }

    @Override
    public Double convertReverse(final String value) {
        try {
            return Double.parseDouble(value);
        } catch (final NumberFormatException exception) {
            LOG.warn("The given String could not be parsed to a Double: " + value, exception);
            throw exception;
        }
    }
}