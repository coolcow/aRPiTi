/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.entityrenderer;

import javax.swing.JPanel;
import org.coolcow.arpiti.backend.entities.Entity;
import org.jdesktop.beansbinding.BindingGroup;

/**
 *
 * @author jruiz
 */
public abstract class AbstractEntityRenderer extends JPanel implements EntityRenderer {
    
    private Entity entity;

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }    
    
}
