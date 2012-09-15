/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.bindingconverter;

import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class IntToStringConverter extends Converter<Integer, String> {

    @Override
    public String convertForward(final Integer value) {
        return Integer.toString(value);
    }

    @Override
    public Integer convertReverse(final String value) {
        return Integer.parseInt(value);
    }
};
