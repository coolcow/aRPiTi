package org.coolcow.arpiti.rptline;

import java.util.Date;
import org.coolcow.arpiti.entities.Entity;

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
    private Entity entity = null;

    public RptLine(final String rawLine, final int number, final Date date, final Type type, final Entity entity) {
        this.rawLine = rawLine;
        this.number = number;
        this.date = date;
        this.type = type;
        this.entity = entity;        
    }

    public Date getDate() {
        return date;
    }

    protected void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    protected void setType(Type type) {
        this.type = type;
    }

    public String getRawLine() {
        return rawLine;
    }

    protected void setRawLine(String rawLine) {
        this.rawLine = rawLine;
    }

    public Entity getEntity() {
        return entity;
    }

    protected void setEntity(Entity entity) {
        this.entity = entity;
    }
    
    public String toString() {
        return rawLine;
    }
    
}
