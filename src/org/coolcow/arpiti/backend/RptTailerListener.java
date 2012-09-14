package org.coolcow.arpiti.backend;

public interface RptTailerListener {

    public void rptLineTailed(RptLine rptLine);
    public void tailingStarted();
    public void tailingFinished();
    
}