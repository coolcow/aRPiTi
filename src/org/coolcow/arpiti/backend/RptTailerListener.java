package org.coolcow.arpiti.backend;

import org.coolcow.arpiti.rptline.RptLine;

public interface RptTailerListener {

    public void rptLineTailed(RptLine rptLine);
    public void tailingStarted();
    public void tailingFinished();
    
}