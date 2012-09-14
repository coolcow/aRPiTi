/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.entities;

import javax.swing.JComponent;

/**
 *
 * @author jruiz
 */
public interface Entity {
    
    public String getRawContent();
    
    public JComponent createRenderer();
}
