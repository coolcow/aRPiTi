package org.coolcow.arpiti.backend.rptline;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.Backend;

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
    private Date time = null;
    private String rawContent;

    @Override
    public Date getTime() {
        if (time == null) {
            return null;
        } else {        
            return (Date) time.clone();
        }
    }

    protected void setTime(final Date time) {
        this.time = time;
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
                date = new SimpleDateFormat(Backend.INPUT_DATE_FORMAT).parse(dateString);
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
                case "HIVE": {
                    type = Type.HIVE; 
                    rptLine = new HiveRptLine();
                } break;
                case "READ/WRITE": { 
                    type = Type.READ_WRITE; 
                    rptLine = new ReadWriteRptLine();
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
        rptLine.setTime(date);
        rptLine.setType(type);
        rptLine.setRawContent(content);
        rptLine.parseLine(rawLine);
        return rptLine;
    }
}
