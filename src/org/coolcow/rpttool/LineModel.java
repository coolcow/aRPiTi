/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.rpttool;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jruiz
 */
public class LineModel extends AbstractTableModel {

    private ArrayList<Line> lines = new ArrayList<>();
    final static String[] COLUMN_NAMES = {"#", "time", "content"};
    final static Class[] COLUMN_CLASSES = {Integer.class, Date.class, String.class};
    final static boolean[] COLUMN_EDITABLE = {false, false, false};

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
        final Line line = lines.get(row);
        switch (column) {
            case 0: {
                return line.getNumber();
            }
            case 1: {
                return line.getDate();
            }
            case 2: {
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

    public void addLine(final Line line) {
        lines.add(line);
        fireTableDataChanged();
    }
    
    public void removeLine(final Line line) {
        lines.remove(line);
        fireTableDataChanged();
    }
    
    final void clear() {
        lines.clear();
        fireTableDataChanged();
    }
    
    final Line getLine(final int row) {
        return lines.get(row);
    }

}
