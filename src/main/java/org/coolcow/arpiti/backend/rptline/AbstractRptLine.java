package org.coolcow.arpiti.backend.rptline;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

/**
 *
 * @author jruiz
 */
public abstract class AbstractRptLine implements RptLine {

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

    public void setTime(final Date time) {
        this.time = time;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    @Override
    public String getRawLine() {
        return rawLine;
    }

    public void setRawLine(final String rawLine) {
        this.rawLine = rawLine;
    }

    @Override
    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(final String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public String toString() {
        return rawLine;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
