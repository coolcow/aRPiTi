/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rpttailer;

import java.util.List;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;

/**
 *
 * @author jruiz
 */
public class RptTailerAdapter implements RptTailerListener {

    @Override
    public void tailingStarted(long byteLength) {
    }

    @Override
    public void tailingProceed(long byteRead) {
    }

    @Override
    public void rptLinesTailed(List<AbstractRptLine> rptLines) {
    }

    @Override
    public void tailingResumed() {
    }

    @Override
    public void tailingWait() {
    }

    @Override
    public void tailingStopped() {
    }
    
}
