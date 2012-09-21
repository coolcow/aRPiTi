/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class DwDebugFpsRptLine extends AbstractRptLine {

    private double fps;

    public DwDebugFpsRptLine() {
        super();
    }

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }

}
