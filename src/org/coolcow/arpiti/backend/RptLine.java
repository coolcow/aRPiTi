package org.coolcow.arpiti.backend;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.coolcow.arpiti.gui.MainFrame;

/**
 *
 * @author jruiz
 */
public class RptLine {

    public enum Type {

        SECOND_HAND_ZOMBIE_INITIALIZED {

            @Override
            public String toString() {
                return "Second Hand Zombie Initialized";
            }
        }, DW_DEBUG_FPS {

            @Override
            public String toString() {
                return "DW_DEBUG FPS";
            }
        }, LOCALITY_EVENT {

            @Override
            public String toString() {
                return "Locality Event";
            }
        }, WRITE, READ_WRITE {

            @Override
            public String toString() {
                return "READ/WRITE";
            }
        }, STARTING_LOGIN {

            @Override
            public String toString() {
                return "STARTING LOGIN";
            }
        }, LOGIN_LOADED {

            @Override
            public String toString() {
                return "LOGIN LOADED";
            }
        }, LOGIN_PUBLISHING {

            @Override
            public String toString() {
                return "LOGIN PUBLISHING";
            }
        }, LOGIN_ATTEMPT {

            @Override
            public String toString() {
                return "LOGIN ATTEMPT";
            }
        }, DISCONNECT_START {

            @Override
            public String toString() {
                return "DISCONNECT START (i)";
            }
        }, PDEATH, DELETE, ERROR, OBJ, HIVE, CLEANUP
    };
    private String rawLine = null;
    private Type type = null;
    private int number = -1;
    private Date date = null;
    private String content = null;

    public RptLine(final String rawLine, final int number, final Date date, final Type type, final String content) {
        this.rawLine = rawLine;
        this.number = number;
        this.date = date;
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRawLine() {
        return rawLine;
    }

    public void setRawLine(String rawLine) {
        this.rawLine = rawLine;
    }
    
    public static RptLine parseLine(final int number, final String rawLine) {
        if (rawLine.trim().length() == 0) {
            return null;
        }
        Type type;
        Date date;
        String content;
        try {
            date = MainFrame.DATE_FORMAT.parse(rawLine.substring(0, 8));
            content = rawLine.substring(9);
        } catch (ParseException ex) {
            date = null;
            content = rawLine;
        }
        if (content.equals("\"" + Type.LOCALITY_EVENT + "\"")) {
            content = "";
            type = Type.LOCALITY_EVENT;
        } else if (content.startsWith("\"" + Type.OBJ + ": ")) {
            content = content.substring(("\"" + Type.OBJ + ": ").length(), content.length() - 1);
            type = Type.OBJ;
        } else if (content.startsWith("\"" + Type.HIVE + ": ")) {
            content = content.substring(("\"" + Type.HIVE + ": ").length(), content.length() - 1);
            type = Type.HIVE;
        } else if (content.startsWith("\"" + Type.WRITE + ": ")) {
            content = content.substring(("\"" + Type.WRITE + ": ").length(), content.length() - 1);
            type = Type.WRITE;
        } else if (content.startsWith("\"" + Type.CLEANUP + ": ")) {
            content = content.substring(("\"" + Type.CLEANUP + ": ").length(), content.length() - 1);
            type = Type.CLEANUP;
        } else if (content.startsWith("\"" + Type.READ_WRITE + ": ")) {
            content = content.substring(("\"" + Type.READ_WRITE + ": ").length(), content.length() - 1);
            type = Type.READ_WRITE;
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
        } else if (content.startsWith("\"" + Type.PDEATH + ": ")) {
            content = content.substring(("\"" + Type.PDEATH + ": ").length(), content.length() - 1);
            type = Type.PDEATH;
        } else if (content.startsWith("\"" + Type.LOGIN_ATTEMPT + ": ")) {
            content = content.substring(("\"" + Type.LOGIN_ATTEMPT + ": ").length(), content.length() - 1);
            type = Type.LOGIN_ATTEMPT;
        } else if (content.startsWith("\"" + Type.LOGIN_LOADED + ": ")) {
            content = content.substring(("\"" + Type.LOGIN_LOADED + ": ").length(), content.length() - 1);
            type = Type.LOGIN_LOADED;
        } else if (content.startsWith("\"" + Type.LOGIN_PUBLISHING + ": ")) {
            content = content.substring(("\"" + Type.LOGIN_PUBLISHING + ": ").length(), content.length() - 1);
            type = Type.LOGIN_PUBLISHING;
        } else if (content.startsWith("\"" + Type.STARTING_LOGIN + ": ")) {
            content = content.substring(("\"" + Type.STARTING_LOGIN + ": ").length(), content.length() - 1);
            type = Type.STARTING_LOGIN;
        } else if (content.startsWith("\"" + Type.DISCONNECT_START + ": ")) {
            content = content.substring(("\"" + Type.DISCONNECT_START + ": ").length(), content.length() - 1);
            type = Type.DISCONNECT_START;
        } else if (content.startsWith("\"" + Type.DELETE + ": ")) {
            content = content.substring(("\"" + Type.DELETE + ": ").length(), content.length() - 1);
            type = Type.DELETE;
        } else if (content.startsWith("\"" + Type.ERROR + ": ")) {
            content = content.substring(("\"" + Type.ERROR + ": ").length(), content.length() - 1);
            type = Type.ERROR;
        } else if (content.startsWith("\"" + Type.DW_DEBUG_FPS + "  : ")) {
            content = content.substring(("\"" + Type.DW_DEBUG_FPS + "  : ").length(), content.length() - 1);
            type = Type.DW_DEBUG_FPS;
        } else if (content.startsWith("\"" + Type.SECOND_HAND_ZOMBIE_INITIALIZED + ": ")) {
            content = content.substring(("\"" + Type.SECOND_HAND_ZOMBIE_INITIALIZED + ": ").length(), content.length() - 1);
            type = Type.SECOND_HAND_ZOMBIE_INITIALIZED;
        } else {
            type = null;
        }
        return new RptLine(rawLine, number, date, type, content);
    }
}
