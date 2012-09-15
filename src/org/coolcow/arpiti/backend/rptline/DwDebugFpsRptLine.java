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

    protected void setFps(double fps) {
        this.fps = fps;
    }

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        try {
            setFps(Double.parseDouble(rawContent));
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }
}
