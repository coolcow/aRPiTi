/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend;

import java.awt.EventQueue;
import java.io.File;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.coolcow.arpiti.gui.MainFrame;

/**
 *
 * @author jruiz
 */
public class Backend {
    
    public static final String INPUT_DATE_FORMAT = "kk:mm:ss";
    
    private static final Logger LOG = Logger.getLogger(Backend.class);
    
    private static Backend INSTANCE;

    private RptTailer rptTailer;
    
    private Backend() {
    }
        
    public static Backend getInstance() {
        if (INSTANCE == null) {
            return (INSTANCE = new Backend());
        } else {
            return INSTANCE;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        configureLocalLog4JAppender();
        configureLookAndFeel();
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final MainFrame frame = new MainFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("MainFrame set to visible");
                }
            }
        });
    }
    
    /**
     * local Log4J appender for BeanMill
     * 
     * (http://blogs.cismet.de/gadgets/beanmill/)
     */
    private static void configureLocalLog4JAppender() {
        final Properties p = new Properties();
        p.put("log4j.appender.Remote", "org.apache.log4j.net.SocketAppender"); // NOI18N
        p.put("log4j.appender.Remote.remoteHost", "localhost");                // NOI18N
        p.put("log4j.appender.Remote.port", "4445");                           // NOI18N
        p.put("log4j.appender.Remote.locationInfo", "true");                   // NOI18N
        p.put("log4j.rootLogger", "DEBUG,Remote");                             // NOI18N
        PropertyConfigurator.configure(p);        
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Logger configured (Except you don't see this log message ;) )");
        }       
    }
    
    private static void configureLookAndFeel() {
        final String laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(laf);
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            LOG.error("LookAndFeel could not be set: " + laf, ex);
        }        
    }    
    
    public void setRptTailer(final RptTailer rptTailer) {
        this.rptTailer = rptTailer;
    }

    public RptTailer getRptTailer() {
        return rptTailer;
    }
    
    public void addRptTailerLister(final RptTailerListener listener) {
        rptTailer.addListener(listener);
    }
    
    public void startNewRptTailer(final File file, final RptTailerListener listener) {
        if (rptTailer != null) {
            rptTailer.stopTailing();
        }
        rptTailer = new RptTailer(file);        
        rptTailer.addListener(listener);
        rptTailer.execute();        
    }
    
    public void pauseRptTailer() {
        rptTailer.setPause(true);
    }
    
    public void resumeRptTailer() {
        rptTailer.setPause(false);        
    }
    
    public void stopRptTailer() {
        rptTailer.stopTailing();
    }
    
}
