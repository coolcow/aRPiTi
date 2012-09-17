/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline.interfaces;

import java.util.Collection;
import org.coolcow.arpiti.backend.Coordinate;

/**
 *
 * @author jruiz
 */
public interface CoordinateProvider {
    
    public Collection<Coordinate> getCoordinates();
    
}
