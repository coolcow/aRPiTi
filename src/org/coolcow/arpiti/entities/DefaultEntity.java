/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.entities;

import javax.swing.JComponent;
import org.coolcow.arpiti.gui.entityrenderer.DefaultRenderer;

/**
 *
 * @author jruiz
 */
public class DefaultEntity extends AbstractEntity {
        
    public DefaultEntity(String rawContent) {
        super(rawContent);
    }

    @Override
    public JComponent createRenderer() {
        return new DefaultRenderer(this);
    }

}
