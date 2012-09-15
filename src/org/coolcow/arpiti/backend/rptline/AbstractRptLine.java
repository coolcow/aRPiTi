package org.coolcow.arpiti.backend.rptline;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.coolcow.arpiti.gui.MainFrame;

/**
 *
 * @author jruiz
 */
public class AbstractRptLine implements RptLine {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private String rawLine = null;
    private Type type = null;
    private int number = -1;
    private Date date = null;
    private String rawContent;

    public AbstractRptLine() {
    }

    @Override
    public Date getDate() {
        return (Date) date.clone();
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
        AbstractRptLine.Type type;
        Date date;
        String content;
        AbstractRptLine rptLine = new DefaultRptLine();
        try {
            date = new SimpleDateFormat(MainFrame.DATE_FORMAT).parse(rawLine.substring(0, 8));
            content = rawLine.substring(9);
        } catch (final ParseException | StringIndexOutOfBoundsException ex) {
            date = null;
            content = rawLine;
        }
        if (content.equals("\"" + AbstractRptLine.Type.LOCALITY_EVENT + "\"")) {
            content = "";
            type = AbstractRptLine.Type.LOCALITY_EVENT;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.OBJ + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.OBJ + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.OBJ;
            rptLine = new LocalityEventRptLine();
        } else if (content.startsWith("\"" + AbstractRptLine.Type.HIVE + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.HIVE + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.HIVE;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.WRITE + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.WRITE + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.WRITE;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.CLEANUP + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.CLEANUP + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.CLEANUP;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.READ_WRITE + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.READ_WRITE + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.READ_WRITE;
//            switch (content.split(Pattern.quote("[")).length) {
//                case 19: {
//                    final int first = content.indexOf("[") + 1;
//                    final int second = content.indexOf("[", first) + 1;
//                    final int third = content.indexOf("[", second) + 1;
//                    final int end = content.indexOf("]", third);
//                    final String substring = content.substring(third, end);
//                    final String[] coordStrings = substring.split(",");
//                    final double x = Double.parseDouble(coordStrings[0]);
//                    final double y = Double.parseDouble(coordStrings[1]);
//                    final double z = Double.parseDouble(coordStrings[2]);
//                }
//                break;
//                case 1: {
//                }
//                break;
//                default: {
//                }
//            }
        } else if (content.startsWith("\"" + AbstractRptLine.Type.PDEATH + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.PDEATH + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.PDEATH;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.LOGIN_ATTEMPT + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.LOGIN_ATTEMPT + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.LOGIN_ATTEMPT;
            rptLine = new LoginAttemptRptLine(content);
        } else if (content.startsWith("\"" + AbstractRptLine.Type.LOGIN_LOADED + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.LOGIN_LOADED + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.LOGIN_LOADED;
            rptLine = new LoginLoadedRptLine();
        } else if (content.startsWith("\"" + AbstractRptLine.Type.LOGIN_PUBLISHING + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.LOGIN_PUBLISHING + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.LOGIN_PUBLISHING;
            rptLine = new LoginPublishingRptLine();
        } else if (content.startsWith("\"" + AbstractRptLine.Type.STARTING_LOGIN + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.STARTING_LOGIN + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.STARTING_LOGIN;
            rptLine = new StartingLoginRptLine();
        } else if (content.startsWith("\"" + AbstractRptLine.Type.DISCONNECT_START_I + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.DISCONNECT_START_I + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.DISCONNECT_START_I;
            rptLine = new DisconnectStartIRptLine();
        } else if (content.startsWith("\"" + AbstractRptLine.Type.DELETE + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.DELETE + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.DELETE;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.ERROR + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.ERROR + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.ERROR;
        } else if (content.startsWith("\"" + AbstractRptLine.Type.DW_DEBUG_FPS + "  : ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.DW_DEBUG_FPS + "  : ").length(), content.length() - 1);
            type = AbstractRptLine.Type.DW_DEBUG_FPS;
            rptLine = new DwDebugFpsRptLine();
        } else if (content.startsWith("\"" + AbstractRptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED + ": ")) {
            content = content.substring(("\"" + AbstractRptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED + ": ").length(), content.length() - 1);
            type = AbstractRptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED;
        } else {
            type = null;
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
