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
    
    private ArrayList<RptLine> lines = new ArrayList<>();
    final static String[] COLUMN_NAMES = {"#", "time", "type", "content"};
    final static Class[] COLUMN_CLASSES = {Integer.class, Date.class, RptLine.Type.class, String.class};
    final static boolean[] COLUMN_EDITABLE = {false, false, false, false};

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
        final RptLine line = lines.get(row);
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
        return lines.size();
    }   

    public void addLine(final RptLine line) {
        lines.add(line);
        fireTableDataChanged();
    }
    
    public void removeLine(final RptLine line) {
        lines.remove(line);
        fireTableDataChanged();
    }
    
    final public void clear() {
        lines.clear();
        fireTableDataChanged();
    }
    
    final public RptLine getLine(final int row) {
        return lines.get(row);
    }

}
