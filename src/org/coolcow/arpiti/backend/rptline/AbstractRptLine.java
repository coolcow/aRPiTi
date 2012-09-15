package org.coolcow.arpiti.backend.rptline;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.coolcow.arpiti.gui.MainFrame;

/**
 *
 * @author jruiz
 */
public abstract class AbstractRptLine implements RptLine {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private String rawLine = null;
    private Type type = null;
    private int number = -1;
    private Date date = null;
    private String rawContent;

    @Override
    public Date getDate() {
        if (date == null) {
            return null;
        } else {        
            return (Date) date.clone();
        }
    }

    protected void setDate(final Date date) {
        this.date = date;
    }

    @Override
    public int getNumber() {
        return number;
    }

    protected void setNumber(final int number) {
        this.number = number;
    }

    @Override
    public Type getType() {
        return type;
    }

    protected void setType(final Type type) {
        this.type = type;
    }

    @Override
    public String getRawLine() {
        return rawLine;
    }

    protected void setRawLine(final String rawLine) {
        this.rawLine = rawLine;
    }

    @Override
    public String getRawContent() {
        return rawContent;
    }

    protected void setRawContent(final String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public String toString() {
        return rawLine;
    }

    @Override
    public boolean parseLine(final String line) {
        return true;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public static AbstractRptLine parseLine(final int number, final String rawLine) {
        if (rawLine.trim().length() == 0) {
            return null;
        }
        AbstractRptLine.Type type = null;
        Date date;
        String rawContent;
        AbstractRptLine rptLine = new DefaultRptLine();
        
        final Matcher timeMatcher = Pattern.compile("^(\\d{1,2}:\\d{2}:\\d{2}) (.*)$").matcher(rawLine.trim());
        if (timeMatcher.matches()) {
            try {
                date = new SimpleDateFormat(MainFrame.INPUT_DATE_FORMAT).parse(timeMatcher.group(1));
            } catch (final ParseException ex) {
                date = null;
            }
            rawContent = timeMatcher.group(2);
        } else {
            date = null;
            rawContent = rawLine;
        }
        
        final String content;
        
        final Matcher localityEventMatcher = Pattern.compile("^\"(" + Type.LOCALITY_EVENT + ")\"$").matcher(rawContent.trim());        
        final Matcher defaultTypeMatcher = Pattern.compile("^\"(.*?): (.*)\"$").matcher(rawContent.trim());        
        if (localityEventMatcher.matches()) {
            rptLine = new LocalityEventRptLine();
            type = Type.LOCALITY_EVENT;
            content = "";
        } else if (defaultTypeMatcher.matches()) {
            final String rawType = defaultTypeMatcher.group(1).trim();
            content = defaultTypeMatcher.group(2);
            switch (rawType) {
                case "LOGIN ATTEMPT": { 
                    type = Type.LOGIN_ATTEMPT; 
                    rptLine = new LoginAttemptRptLine(); 
                } break;
                case "LOGIN LOADED": { 
                    type = Type.LOGIN_LOADED; 
                    rptLine = new LoginLoadedRptLine();
                } break;
                case "LOGIN PUBLISHING": { 
                    type = Type.LOGIN_PUBLISHING; 
                    rptLine = new LoginPublishingRptLine();
                } break;
                case "STARTING LOGIN": { 
                    type = Type.STARTING_LOGIN; 
                    rptLine = new StartingLoginRptLine();
                } break;
                case "DISCONNECT START (i)": { 
                    type = Type.DISCONNECT_START_I; 
                    rptLine = new DisconnectStartIRptLine();
                } break;
                case "DW_DEBUG FPS": { 
                    type = Type.DW_DEBUG_FPS; 
                    rptLine = new DwDebugFpsRptLine();
                } break;
                case "OBJ": { 
                    type = Type.OBJ; 
                    rptLine = new ObjRptLine();
                } break;
                case "DELETE": { type = Type.DELETE; } break;
                case "ERROR": { type = Type.ERROR; } break;
                case "Second Hand Zombie Initialized": { type = Type.SECOND_HAND_ZOMBIE_INITIALIZED; } break;
                case "HIVE": { type = Type.HIVE; } break;
                case "WRITE": { type = Type.WRITE; } break;
                case "CLEANUP": { type = Type.CLEANUP; } break;
                case "READ/WRITE": { 
                    type = Type.READ_WRITE; 
//                    switch (content.split(Pattern.quote("[")).length) {
//                        case 19: {
//                            final int first = content.indexOf("[") + 1;
//                            final int second = content.indexOf("[", first) + 1;
//                            final int third = content.indexOf("[", second) + 1;
//                            final int end = content.indexOf("]", third);
//                            final String substring = content.substring(third, end);
//                            final String[] coordStrings = substring.split(",");
//                            final double x = Double.parseDouble(coordStrings[0]);
//                            final double y = Double.parseDouble(coordStrings[1]);
//                            final double z = Double.parseDouble(coordStrings[2]);
//                        }
//                        break;
//                        case 1: {
//                        }
//                        break;
//                        default: {
//                        }
//                    }
                } break;
                case "PDEATH": { type = Type.PDEATH; } break;
                default: {
                }
            }
        } else {
            type = null;
            content = rawContent;
        }
        rptLine.setRawLine(rawLine);
        rptLine.setNumber(number);
        rptLine.setDate(date);
        rptLine.setType(type);
        rptLine.setRawContent(content);
        rptLine.parseLine(rawLine);
        return rptLine;
    }
}
