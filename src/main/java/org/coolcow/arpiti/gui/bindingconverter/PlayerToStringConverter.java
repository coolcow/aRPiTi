/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.bindingconverter;

import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.entities.Coordinate;
import org.coolcow.arpiti.backend.entities.Player;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author jruiz
 */
public class PlayerToStringConverter extends Converter<Player, String> {

    private static final Logger LOG = Logger.getLogger(PlayerToStringConverter.class);
    
    @Override
    public String convertForward(final Player value) {
        return Player.toString(value);
    }

    @Override
    public Player convertReverse(final String value) {
        return null; //not implemented yet
    }
}