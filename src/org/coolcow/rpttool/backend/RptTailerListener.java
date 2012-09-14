package org.coolcow.rpttool.backend;

public interface RptTailerListener {

    public void rptLineTailed(RptLine rptLine);
    public void tailingStarted();
    public void tailingFinished();
    
}