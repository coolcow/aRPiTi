/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.bindingconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class DateToStringConverter extends Converter<Date, String> {

    private static final Logger LOG = Logger.getLogger(DateToStringConverter.class);
    private static final String DATE_FORMAT = "dd.MM.yyyy, HH:mm";
    
    @Override
    public String convertForward(final Date value) {
        return new SimpleDateFormat(DATE_FORMAT).format(value);
    }

    @Override
    public Date convertReverse(final String value) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(value);
        } catch (final ParseException exception) {
            LOG.warn("The given String could not be parsed to a Date: " + value, exception);
            throw new IllegalArgumentException(exception);
        }
    }
}