package org.coolcow.arpiti.backend;

import org.coolcow.arpiti.rptline.RptLineFactory;
import org.coolcow.arpiti.rptline.RptLine;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;

public class RptTailer extends Thread {

    private final File rptFile;
    private long sampleInterval = 1000;
    private boolean tailing = false;
    private boolean autorefresh = true;
    private boolean pause = false;
    private Collection<RptTailerListener> listeners = new ArrayList<>();

    public RptTailer(File rptFile) {
        this.rptFile = rptFile;
    }

    public void addLogFileTailerListener(final RptTailerListener listener) {
        this.listeners.add(listener);
    }

    public void removeLogFileTailerListener(final RptTailerListener listener) {
        this.listeners.remove(listener);
    }

    protected void fireRptLineTailed(final RptLine rptLine) {
        for (final RptTailerListener listener : listeners) {
            listener.rptLineTailed(rptLine);
        }
    }

    protected void fireTailingStarted() {
        for (final RptTailerListener listener : listeners) {
            listener.tailingStarted();
        }
    }

    protected void fireTailingFinished() {
        for (final RptTailerListener listener : listeners) {
            listener.tailingFinished();
        }
    }
    
    public void stopTailing() {
        this.tailing = false;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isAutorefresh() {
        return autorefresh;
    }

    public void setAutorefresh(boolean autorefresh) {
        this.autorefresh = autorefresh;
    }    
    
    @Override
    public void run() {
        long filePointer = 0;
        int lineNumber = 0;
        RandomAccessFile file = null;
        try {
            this.tailing = true;
            while (this.tailing) {
                try {
                    long fileLength = this.rptFile.length();
                    if (file == null || fileLength < filePointer) {
                        file = new RandomAccessFile(rptFile, "r");
                        filePointer = 0;
                    }

                    if (fileLength > filePointer) {
                        fireTailingStarted();
                        file.seek(filePointer);
                        String lineString ;
                        while ((lineString = file.readLine()) != null) {
                            while (pause) {
                                sleep(100);
                            }
                            lineNumber++;
                            final RptLine rptLine = RptLineFactory.parseLine(lineNumber, lineString);                            
                            fireRptLineTailed(rptLine);
                        }
                        filePointer = file.getFilePointer();
                        fireTailingFinished();
                    }

                    sleep(this.sampleInterval);
                    while (!autorefresh) {
                        sleep(100);                        
                    }
                } catch (IOException | InterruptedException e) {
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
            }
        }
    }
}