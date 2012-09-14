/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.entityrenderer;

import org.coolcow.arpiti.backend.entities.Entity;

/**
 *
 * @author jruiz
 */
public interface EntityRenderer {
    
    Entity getEntity();
    
    void setEntity(final Entity entity);
    
}
