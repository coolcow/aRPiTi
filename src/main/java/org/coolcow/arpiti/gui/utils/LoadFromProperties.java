/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Ham
 */
public class LoadFromProperties {

    public static final String fileOptions = "options.properties";
    private static final Logger LOG = Logger.getLogger(LoadFromProperties.class);

    public static void storeRptPath(File filepath) {
        try {

            File options = new File(fileOptions);
            Properties prop = new Properties();
            prop.setProperty("rptPath", filepath.getParent());
            BufferedWriter br = new BufferedWriter(new FileWriter(options));
            prop.store(br, "Properties of the aRPiTi frame");

        } catch (IOException ex) {
            LOG.error("Error while writing options properties", ex);
        }



    }

    public static File restoreRptPath() {

        File directoryToOpen = null;

        try {

            File file = new File(fileOptions);
            Properties prop = new Properties();
            BufferedReader br = new BufferedReader(new FileReader(file));
            prop.load(br);
            String rptPathName = prop.getProperty("rptPath");
            directoryToOpen = new File(rptPathName);

        } catch (IOException ex) {
            LOG.error("Options File Not found", ex);
        }
        return directoryToOpen;

    }
}
