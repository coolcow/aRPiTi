/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.rptline;

import javax.swing.JPanel;
import org.coolcow.arpiti.backend.rptline.RptLine;

/**
 *
 * @author jruiz
 */
public abstract class AbstractRptLineRenderer extends JPanel implements RptLineRenderer {

    private RptLine rptLine;

    @Override
    public RptLine getRptLine() {
        return rptLine;
    }

    @Override
    public void setRptLine(final RptLine rptLine) {
        this.rptLine = rptLine;
    }
}
