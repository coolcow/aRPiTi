package org.coolcow.rpttool.backend;

public interface RptTailerListener {

    public void newLogFileLine(String line);
}