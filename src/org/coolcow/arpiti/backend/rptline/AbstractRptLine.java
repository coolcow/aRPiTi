package org.coolcow.arpiti.backend.rptline;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.gui.MainFrame;

/**
 *
 * @author jruiz
 */
public abstract class AbstractRptLine implements RptLine {

    private static final Logger LOG = Logger.getLogger(AbstractRptLine.class);
    
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
            final String dateString = timeMatcher.group(1);
            final String contentString = timeMatcher.group(2);
            
            try {
                date = new SimpleDateFormat(MainFrame.INPUT_DATE_FORMAT).parse(dateString);
            } catch (final ParseException exception) {
                LOG.warn("Error while parsing date. Date is set to null. The source String was: " + dateString, exception);
                date = null;
            }
            rawContent = contentString;
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
            final String typeString = defaultTypeMatcher.group(1).trim();
            final String contentString = defaultTypeMatcher.group(2);
            
            content = contentString;
            switch (typeString) {
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
                case "ERROR": { 
                    type = Type.ERROR; 
                    rptLine = new ErrorRptLine();
                } break;
                case "Second Hand Zombie Initialized": { 
                    type = Type.SECOND_HAND_ZOMBIE_INITIALIZED; 
                    rptLine = new SecondHandZombieInitializedRptLine();
                } break;
                case "PDEATH": { 
                    type = Type.PDEATH; 
                    rptLine = new PDeathRptLine();
                } break;
                case "WRITE": { 
                    type = Type.WRITE; 
                    rptLine = new WriteRptLine();
                } break;
                case "CLEANUP": { 
                    type = Type.CLEANUP; 
                    rptLine = new CleanupRptLine();
                } break;
                case "DELETE": { 
                    type = Type.DELETE; 
                } break;
                case "HIVE": { type = Type.HIVE; } break;
                case "READ/WRITE": { 
                    type = Type.READ_WRITE; 
                    rptLine = new ReadWriteRptLine();
                    //['PASS',[false,false,false,true,true,false,true,7083.59,["Pelvis","aimpoint","RightFoot"],[1.10509,0],0,[1115.25,926.767]],[6,3,0,0],["MP5A5","amovppnemstpsraswrfldnon",42],[294,[4684.51,10477.9,0.00143433]],2650]
                    //^\['(\w+)',\[((true,|false,)*)(.+?),\[((\"\w+\",?)*?)\],\[(.*?)\](.*),(\d+)\]$
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
