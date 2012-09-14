/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.entities;


import javax.swing.JComponent;
import org.coolcow.arpiti.gui.entityrenderer.LocalityEventRenderer;

/**
 *
 * @author jruiz
 */
public class LocalityEventEntity extends AbstractEntity {
    
    
    public LocalityEventEntity(final String content) {
        super(content);
    }

    @Override
    public JComponent createRenderer() {
        return new LocalityEventRenderer(this);
    }
    
}
