/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline.interfaces;

import java.util.Collection;
import org.coolcow.arpiti.backend.Item;

/**
 *
 * @author jruiz
 */
public interface ItemProvider {
    
    public Collection<Item> getItems();
    
}
