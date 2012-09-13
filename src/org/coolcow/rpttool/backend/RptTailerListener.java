package org.coolcow.rpttool.backend;

public interface RptTailerListener {

    public void newLineTailed(String line);
    public void tailingStarted();
    public void tailingFinished();
    
}