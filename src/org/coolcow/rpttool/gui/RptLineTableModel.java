/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.rpttool.gui;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import org.coolcow.rpttool.backend.RptLine;

/**
 *
 * @author jruiz
 */
public class RptLineTableModel extends AbstractTableModel {

    public static final int COLUMN_NUMBER = 0;
    public static final int COLUMN_TIME = 1;
    public static final int COLUMN_TYPE = 2;
    public static final int COLUMN_CONTENT = 3;    
    public static final String[] COLUMN_NAMES = {"#", "time", "type", "content"};
    public static final Class[] COLUMN_CLASSES = {Integer.class, Date.class, RptLine.Type.class, String.class};
    public static final boolean[] COLUMN_EDITABLE = {false, false, false, false};

    private final ArrayList<RptLine> lines = new ArrayList<>();
    
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        return COLUMN_EDITABLE[columnIndex];
    }        

    @Override
    public Object getValueAt(int row, int column) {
        final RptLine line;
        synchronized(lines) {
            line = lines.get(row);
        }
        switch (column) {
            case COLUMN_NUMBER: {
                return line.getNumber();
            }
            case COLUMN_TIME: {
                return line.getDate();
            }
            case COLUMN_TYPE: {
                return line.getType();
            }
            case COLUMN_CONTENT: {
                return line.getContent();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getRowCount() {
        final int rowCount;
        synchronized(lines) {
            rowCount = lines.size();
        }
        return rowCount;
    }   

    public void addLine(final RptLine line) {
        synchronized(lines) {
            lines.add(line);
            fireTableDataChanged();
        }
    }
    
    public void removeLine(final RptLine line) {
        synchronized(lines) {
            lines.remove(line);
            fireTableDataChanged();
        }
    }
    
    final public void clear() {
        synchronized(lines) {
            lines.clear();
            fireTableDataChanged();
        }
    }
    
    final public RptLine getLine(final int row) {
        final RptLine line;
        synchronized(lines) {
            line = lines.get(row);
        }
        return line;
    }

}