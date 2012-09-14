/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.coolcow.arpiti.gui.entityrenderer.DwDebugFpsRenderer;

/**
 *
 * @author jruiz
 */
public abstract class AbstractEntity implements Entity {
        
    private String rawContent;
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    public AbstractEntity(final String rawContent) {
        this.rawContent = rawContent;        
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }      
    
    @Override
    public String getRawContent() {
        return rawContent;
    }

    protected void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }
    
}
