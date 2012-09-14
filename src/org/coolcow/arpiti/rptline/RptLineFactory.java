/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.rptline;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;
import org.coolcow.arpiti.entities.DefaultEntity;
import org.coolcow.arpiti.entities.DwDebugFpsEntity;
import org.coolcow.arpiti.entities.Entity;
import org.coolcow.arpiti.entities.LocalityEventEntity;
import org.coolcow.arpiti.gui.MainFrame;

/**
 *
 * @author jruiz
 */
public class RptLineFactory {
        
    public static RptLine parseLine(final int number, final String rawLine) {
        if (rawLine.trim().length() == 0) {
            return null;
        }
        RptLine.Type type;
        Date date;
        String content;
        Entity entity = null;
        try {
            date = MainFrame.DATE_FORMAT.parse(rawLine.substring(0, 8));
            content = rawLine.substring(9);
        } catch (ParseException ex) {
            date = null;
            content = rawLine;
        }
        if (content.equals("\"" + RptLine.Type.LOCALITY_EVENT + "\"")) {
            content = "";
            type = RptLine.Type.LOCALITY_EVENT;
        } else if (content.startsWith("\"" + RptLine.Type.OBJ + ": ")) {
            content = content.substring(("\"" + RptLine.Type.OBJ + ": ").length(), content.length() - 1);
            type = RptLine.Type.OBJ;
            entity = new LocalityEventEntity(content);      
        } else if (content.startsWith("\"" + RptLine.Type.HIVE + ": ")) {
            content = content.substring(("\"" + RptLine.Type.HIVE + ": ").length(), content.length() - 1);
            type = RptLine.Type.HIVE;
        } else if (content.startsWith("\"" + RptLine.Type.WRITE + ": ")) {
            content = content.substring(("\"" + RptLine.Type.WRITE + ": ").length(), content.length() - 1);
            type = RptLine.Type.WRITE;
        } else if (content.startsWith("\"" + RptLine.Type.CLEANUP + ": ")) {
            content = content.substring(("\"" + RptLine.Type.CLEANUP + ": ").length(), content.length() - 1);
            type = RptLine.Type.CLEANUP;
        } else if (content.startsWith("\"" + RptLine.Type.READ_WRITE + ": ")) {
            content = content.substring(("\"" + RptLine.Type.READ_WRITE + ": ").length(), content.length() - 1);
            type = RptLine.Type.READ_WRITE;
            switch (content.split(Pattern.quote("[")).length) {
                case 19: {
                    final int first = content.indexOf("[") + 1;
                    final int second = content.indexOf("[", first) + 1;
                    final int third = content.indexOf("[", second) + 1;
                    final int end = content.indexOf("]", third);
                    final String substring = content.substring(third, end);
                    final String[] coordStrings = substring.split(",");
                    final double x = Double.parseDouble(coordStrings[0]);
                    final double y = Double.parseDouble(coordStrings[1]);
                    final double z = Double.parseDouble(coordStrings[2]);
//                    System.out.println(x + ", " + y + " : "+ contentString);                    
                }
                break;
                case 1: {
                }
                break;
                default: {
                }
            }
        } else if (content.startsWith("\"" + RptLine.Type.PDEATH + ": ")) {
            content = content.substring(("\"" + RptLine.Type.PDEATH + ": ").length(), content.length() - 1);
            type = RptLine.Type.PDEATH;
        } else if (content.startsWith("\"" + RptLine.Type.LOGIN_ATTEMPT + ": ")) {
            content = content.substring(("\"" + RptLine.Type.LOGIN_ATTEMPT + ": ").length(), content.length() - 1);
            type = RptLine.Type.LOGIN_ATTEMPT;
        } else if (content.startsWith("\"" + RptLine.Type.LOGIN_LOADED + ": ")) {
            content = content.substring(("\"" + RptLine.Type.LOGIN_LOADED + ": ").length(), content.length() - 1);
            type = RptLine.Type.LOGIN_LOADED;
        } else if (content.startsWith("\"" + RptLine.Type.LOGIN_PUBLISHING + ": ")) {
            content = content.substring(("\"" + RptLine.Type.LOGIN_PUBLISHING + ": ").length(), content.length() - 1);
            type = RptLine.Type.LOGIN_PUBLISHING;
        } else if (content.startsWith("\"" + RptLine.Type.STARTING_LOGIN + ": ")) {
            content = content.substring(("\"" + RptLine.Type.STARTING_LOGIN + ": ").length(), content.length() - 1);
            type = RptLine.Type.STARTING_LOGIN;
        } else if (content.startsWith("\"" + RptLine.Type.DISCONNECT_START + ": ")) {
            content = content.substring(("\"" + RptLine.Type.DISCONNECT_START + ": ").length(), content.length() - 1);
            type = RptLine.Type.DISCONNECT_START;
        } else if (content.startsWith("\"" + RptLine.Type.DELETE + ": ")) {
            content = content.substring(("\"" + RptLine.Type.DELETE + ": ").length(), content.length() - 1);
            type = RptLine.Type.DELETE;
        } else if (content.startsWith("\"" + RptLine.Type.ERROR + ": ")) {
            content = content.substring(("\"" + RptLine.Type.ERROR + ": ").length(), content.length() - 1);
            type = RptLine.Type.ERROR;
        } else if (content.startsWith("\"" + RptLine.Type.DW_DEBUG_FPS + "  : ")) {
            content = content.substring(("\"" + RptLine.Type.DW_DEBUG_FPS + "  : ").length(), content.length() - 1);
            type = RptLine.Type.DW_DEBUG_FPS;
            entity = new DwDebugFpsEntity(content);
        } else if (content.startsWith("\"" + RptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED + ": ")) {
            content = content.substring(("\"" + RptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED + ": ").length(), content.length() - 1);
            type = RptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED;
        } else {
            type = null;
        }
        if (entity == null) {
            entity = new DefaultEntity(content);        
        }
        return new RptLine(rawLine, number, date, type, entity);
    }
}
