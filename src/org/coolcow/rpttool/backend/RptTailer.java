package org.coolcow.rpttool.backend;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RptTailer extends Thread {

    private long sampleInterval = 5000;
    private File logfile;
    private boolean tailing = false;
    private Set listeners = new HashSet();

    public RptTailer(File file) {
        this.logfile = file;
    }

    public RptTailer(final File file, final long sampleInterval) {
        this.logfile = file;
        this.sampleInterval = sampleInterval;
    }

    public void addLogFileTailerListener(final RptTailerListener listener) {
        this.listeners.add(listener);
    }

    public void removeLogFileTailerListener(final RptTailerListener listener) {
        this.listeners.remove(listener);
    }

    protected void fireNewLogFileLine(final String line) {
        for (Iterator iterator = this.listeners.iterator(); iterator.hasNext();) {
            RptTailerListener listener = (RptTailerListener) iterator.next();
            listener.newLogFileLine(line);
        }
    }

    public void stopTailing() {
        this.tailing = false;
    }

    @Override
    public void run() {
        long filePointer;

        filePointer = 0;
        RandomAccessFile file = null;
        try {
            this.tailing = true;
            while (this.tailing) {
                try {
                    long fileLength = this.logfile.length();
                    if (file == null || fileLength < filePointer) {
                        file = new RandomAccessFile(logfile, "r");
                        filePointer = 0;
                    }

                    if (fileLength > filePointer) {
                        file.seek(filePointer);
                        String line = file.readLine();
                        while (line != null) {
                            this.fireNewLogFileLine(line);
                            line = file.readLine();
                        }
                        filePointer = file.getFilePointer();
                    }

                    sleep(this.sampleInterval);
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