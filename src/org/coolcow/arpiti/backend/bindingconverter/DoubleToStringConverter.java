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
public class DoubleToStringConverter extends Converter<Double, String> {

    @Override
    public String convertForward(Double value) {
        return Double.toString(value);
    }

    @Override
    public Double convertReverse(String value) {
        return Double.parseDouble(value);
    }

}