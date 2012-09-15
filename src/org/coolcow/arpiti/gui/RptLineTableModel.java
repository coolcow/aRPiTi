/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;

/**
 *
 * @author jruiz
 */
public class RptLineTableModel extends AbstractTableModel {

    public static final int COLUMN_NUMBER = 0;
    public static final int COLUMN_TIME = 1;
    public static final int COLUMN_TYPE = 2;
    public static final int COLUMN_CONTENT = 3;
    static final boolean[] COLUMN_EDITABLE = {false, false, false, false};
    static final Class[] COLUMN_CLASSES = {Integer.class, Date.class, AbstractRptLine.Type.class, String.class};
    static final String[] COLUMN_NAMES = {"#", "time", "type", "content"};
    private final ArrayList<AbstractRptLine> lines = new ArrayList<>();

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
        final AbstractRptLine line;
        synchronized (lines) {
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
                return line.getRawContent();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getRowCount() {
        final int rowCount;
        synchronized (lines) {
            rowCount = lines.size();
        }
        return rowCount;
    }

    public void addLine(final AbstractRptLine line) {
        synchronized (lines) {
            lines.add(line);
            fireTableRowsInserted(lines.size() - 1, lines.size() - 1);
        }
    }

    public void addLines(final List<AbstractRptLine> lines) {
        synchronized (lines) {
            this.lines.addAll(lines);
            fireTableRowsInserted(this.lines.size() - lines.size(), this.lines.size() - 1);
        }
    }

    public void removeLine(final AbstractRptLine line) {
        synchronized (lines) {
            lines.remove(line);
            fireTableDataChanged();
        }
    }

    final public void clear() {
        synchronized (lines) {
            lines.clear();
            fireTableDataChanged();
        }
    }

    final public AbstractRptLine getLine(final int row) {
        final AbstractRptLine line;
        synchronized (lines) {
            line = lines.get(row);
        }
        return line;
    }
}
