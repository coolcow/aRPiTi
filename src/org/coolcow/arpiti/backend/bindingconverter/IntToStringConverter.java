/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.bindingconverter;

import org.apache.log4j.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class IntToStringConverter extends Converter<Integer, String> {

    private static final Logger LOG = Logger.getLogger(IntToStringConverter.class);
    
    @Override
    public String convertForward(final Integer value) {
        return Integer.toString(value);
    }

    @Override
    public Integer convertReverse(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (final NumberFormatException exception) {
            LOG.warn("The given String could not be parsed to an Integer: " + value, exception);
            throw exception;
        }
        
    }
};
