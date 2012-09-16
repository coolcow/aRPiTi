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
public class DefaultRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(DefaultRptLine.class);
    
    public DefaultRptLine() {
        super();
    }

    @Override
    public boolean parseLine(String line) {
        return super.parseLine(line);
    }
}
