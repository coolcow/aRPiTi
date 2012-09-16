package org.coolcow.arpiti.backend;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;

public class RptTailer extends SwingWorker<Void, Void> {

    private final File rptFile;
    private long flushInterval = 1000;
    private long refreshInterval = 1000;
    private boolean tailing = false;
    private boolean pause = false;
    private List<AbstractRptLine> collectedRptLines = new ArrayList<>();
    private Collection<RptTailerListener> listeners = new ArrayList<>();
    
    private static final Logger LOG = Logger.getLogger(RptTailer.class);
            
    public RptTailer(File rptFile) {
        this.rptFile = rptFile;
    }

    public void addLogFileTailerListener(final RptTailerListener listener) {
        this.listeners.add(listener);
    }

    public void removeLogFileTailerListener(final RptTailerListener listener) {
        this.listeners.remove(listener);
    }

    protected void fireRptLineTailed(final AbstractRptLine rptLine) {
        for (final RptTailerListener listener : listeners) {
            listener.rptLineTailed(rptLine);
        }
    }

    protected void fireRptLinesTailed(final List<AbstractRptLine> rptLines) {
        for (final RptTailerListener listener : listeners) {
            listener.rptLinesTailed(rptLines);
        }
    }

    protected void fireTailingResumed() {
        for (final RptTailerListener listener : listeners) {
            listener.tailingResumed();
        }
    }

    protected void fireTailingWait() {
        for (final RptTailerListener listener : listeners) {
            listener.tailingWait();
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

    @Override
    protected Void doInBackground() throws Exception {
        long filePointer = 0;
        int lineNumber = 0;
        RandomAccessFile file = null;
        try {
            this.tailing = true;
            while (this.tailing) {
                long timeInMs = System.currentTimeMillis();
                try {
                    long fileLength = this.rptFile.length();
                    if (file == null || fileLength < filePointer) {
                        file = new RandomAccessFile(rptFile, "r");
                        filePointer = 0;
                    }

                    if (fileLength > filePointer) {
                        fireTailingResumed();
                        file.seek(filePointer);
                        String lineString;
                        while ((lineString = file.readLine()) != null) {
                            if (pause) {
                                fireTailingWait();
                                while (pause) {
                                    Thread.sleep(100);
                                }
                                fireTailingResumed();
                            }
                            lineNumber++;
                            final AbstractRptLine rptLine = AbstractRptLine.parseLine(lineNumber, lineString);
                            if (rptLine != null) {
                                collectedRptLines.add(rptLine);
                            }

                            final long newTimeInMs = System.currentTimeMillis();
                            if (newTimeInMs - timeInMs > flushInterval) {
                                timeInMs = newTimeInMs;
                                fireRptLinesTailed(collectedRptLines);
                                collectedRptLines = new ArrayList<>();
                            }
                        }
                        filePointer = file.getFilePointer();
                    }
                } catch (final IOException | InterruptedException exception) {
                    LOG.error("Error while reading line", exception);
                }
                
                if (collectedRptLines.size() > 0) {
                    Thread.sleep(System.currentTimeMillis() - timeInMs);
                    fireRptLinesTailed(collectedRptLines);
                    collectedRptLines = new ArrayList<>();
                } else {
                    fireTailingWait();
                    Thread.sleep(refreshInterval);
                }
            }
        } catch (final RuntimeException exception) {
            LOG.fatal("Error while tailing RPT file.", exception);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (final IOException exception) {
                LOG.fatal("File could not be closed normaly (maybe the file is already closed or does not exists anymore ?).", exception);
            }
        }
        return null;
    }
}