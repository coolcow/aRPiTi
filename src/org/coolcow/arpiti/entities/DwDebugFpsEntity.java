/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.entities;

import javax.swing.JComponent;
import org.coolcow.arpiti.gui.entityrenderer.DwDebugFpsRenderer;

/**
 *
 * @author jruiz
 */
public class DwDebugFpsEntity extends AbstractEntity {
    
    private double fps;
    
    public DwDebugFpsEntity(final String content) {
        super(content);
        fps = Double.parseDouble(content);
    }
    
    public double getFps() {
        return fps;
    }

    @Override
    public JComponent createRenderer() {
        return new DwDebugFpsRenderer(this);
    }
    
}
