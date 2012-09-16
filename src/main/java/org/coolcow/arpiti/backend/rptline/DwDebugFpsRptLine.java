/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import org.apache.log4j.Logger;

/**
 *
 * @author jruiz
 */
public class DwDebugFpsRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(DwDebugFpsRptLine.class);
    
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
        final String fpsString = rawContent;
                
        try {
            setFps(Double.parseDouble(fpsString));
        } catch (final NumberFormatException exception) {
            LOG.warn("Error while parsing fps. Set to -1. The source String was: " + fpsString, exception);
            setFps(-1);
        }
        
        return true;
    }
}
