/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.RowFilter.Entry;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.Backend;
import org.coolcow.arpiti.backend.RptLineRendererProvider;
import org.coolcow.arpiti.backend.RptTailerListener;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;
import org.coolcow.arpiti.gui.rptline.AbstractRptLineRenderer;

/**
 *
 * @author jruiz
 */
public final class MainFrame extends javax.swing.JFrame {

    public final static String OUTPUT_DATE_FORMAT = "HH:mm:ss";
    private final RptLineTableModel model = new RptLineTableModel();
    private File rptFile = null;
    private boolean autoscroll = true;
    private final TableRowSorter<RptLineTableModel> sorter = new TableRowSorter<>(model);
    private AbstractRptLine popLine = null;
    
    private static final Logger LOG = Logger.getLogger(MainFrame.class);

    /**
     * Creates new form NewJFrame
     */
    public MainFrame() {
        initComponents();

        sorter.setRowFilter(new RowFilterImpl());
        tblLines.setRowSorter(sorter);
        tblLines.setAutoCreateRowSorter(false);
        tblLines.setDefaultRenderer(Date.class, new RptTableDateCellRenderer());
        tblLines.addMouseListener(new RptTableMouseListener());
        tblLines.getSelectionModel().addListSelectionListener(new RptTableSelectionListener());

        final TableColumnModel columnModel = tblLines.getColumnModel();
        columnModel.getColumn(RptLineTableModel.COLUMN_NUMBER).setPreferredWidth(60);
        columnModel.getColumn(RptLineTableModel.COLUMN_TIME).setPreferredWidth(60);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pumTableitem = new javax.swing.JPopupMenu();
        mniCopy = new javax.swing.JMenuItem();
        panMain = new javax.swing.JPanel();
        panProgress = new javax.swing.JPanel();
        pgbLines = new javax.swing.JProgressBar();
        tgbResume = new javax.swing.JToggleButton();
        tgbAutoScroll = new javax.swing.JToggleButton();
        panTable = new javax.swing.JPanel();
        scrTable = new javax.swing.JScrollPane();
        tblLines = new CorrectLayoutJTable();
        panRight = new javax.swing.JPanel();
        panInfo = new javax.swing.JPanel();
        panFilter = new javax.swing.JPanel();
        txtFilter = new javax.swing.JTextField();
        panTypes = new javax.swing.JPanel();
        cbxFilterNull = new javax.swing.JCheckBox();
        cbxFilterObj = new javax.swing.JCheckBox();
        cbxFilterCleanup = new javax.swing.JCheckBox();
        cbxFilterWrite = new javax.swing.JCheckBox();
        cbxFilterReadWrite = new javax.swing.JCheckBox();
        cbxFilterStartingLogin = new javax.swing.JCheckBox();
        cbxFilterLoginAttempt = new javax.swing.JCheckBox();
        cbxFilterLoginLoaded = new javax.swing.JCheckBox();
        cbxFilterLoginPublishing = new javax.swing.JCheckBox();
        cbxFilterDisconnectStartI = new javax.swing.JCheckBox();
        cbxFilterHive = new javax.swing.JCheckBox();
        cbxFilterPdeath = new javax.swing.JCheckBox();
        cbxFilterDelete = new javax.swing.JCheckBox();
        cbxFilterLocalityEvent = new javax.swing.JCheckBox();
        cbxFilterError = new javax.swing.JCheckBox();
        cbxFilterDwDebugFps = new javax.swing.JCheckBox();
        cbxFilterSecondHandZombieInitialized = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        panPlayers = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        cbFilterPlayers = new javax.swing.JCheckBox();
        btnFilterApply = new javax.swing.JButton();
        mnbMain = new javax.swing.JMenuBar();
        menMain = new javax.swing.JMenu();
        mniLoadRpt = new javax.swing.JMenuItem();
        mniExit = new javax.swing.JMenuItem();

        mniCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/coolcow/arpiti/gui/copy.png"))); // NOI18N
        mniCopy.setText("copy to clipboard");
        mniCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCopyActionPerformed(evt);
            }
        });
        pumTableitem.add(mniCopy);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("aRPiTi (RPT analysis tool)  - by coolcow [ALPHA version]");
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(1024, 780));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panMain.setLayout(new java.awt.GridBagLayout());

        panProgress.setLayout(new java.awt.GridBagLayout());

        pgbLines.setFocusable(false);
        pgbLines.setPreferredSize(new java.awt.Dimension(146, 20));
        pgbLines.setString("0 lines");
        pgbLines.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panProgress.add(pgbLines, gridBagConstraints);

        tgbResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/coolcow/arpiti/gui/resume.png"))); // NOI18N
        tgbResume.setEnabled(false);
        tgbResume.setFocusPainted(false);
        tgbResume.setFocusable(false);
        tgbResume.setMaximumSize(new java.awt.Dimension(100, 25));
        tgbResume.setMinimumSize(new java.awt.Dimension(100, 25));
        tgbResume.setPreferredSize(new java.awt.Dimension(100, 25));
        tgbResume.setRequestFocusEnabled(false);
        tgbResume.setRolloverEnabled(false);
        tgbResume.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/org/coolcow/arpiti/gui/pause.png"))); // NOI18N
        tgbResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbResumeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panProgress.add(tgbResume, gridBagConstraints);

        tgbAutoScroll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/coolcow/arpiti/gui/autoscroll.png"))); // NOI18N
        tgbAutoScroll.setSelected(true);
        tgbAutoScroll.setFocusPainted(false);
        tgbAutoScroll.setFocusable(false);
        tgbAutoScroll.setMaximumSize(new java.awt.Dimension(25, 25));
        tgbAutoScroll.setMinimumSize(new java.awt.Dimension(25, 25));
        tgbAutoScroll.setPreferredSize(new java.awt.Dimension(25, 25));
        tgbAutoScroll.setRequestFocusEnabled(false);
        tgbAutoScroll.setRolloverEnabled(false);
        tgbAutoScroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbAutoScrollActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panProgress.add(tgbAutoScroll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panMain.add(panProgress, gridBagConstraints);

        panTable.setLayout(new java.awt.GridBagLayout());

        tblLines.setModel(model);
        tblLines.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tblLines.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblLines.getTableHeader().setReorderingAllowed(false);
        scrTable.setViewportView(tblLines);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panTable.add(scrTable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panMain.add(panTable, gridBagConstraints);
        panTable.getAccessibleContext().setAccessibleName("");

        panRight.setLayout(new java.awt.GridBagLayout());

        panInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Details about selected row"));
        panInfo.setLayout(new javax.swing.BoxLayout(panInfo, javax.swing.BoxLayout.LINE_AXIS));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panRight.add(panInfo, gridBagConstraints);

        panFilter.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));
        panFilter.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panFilter.add(txtFilter, gridBagConstraints);

        panTypes.setBorder(javax.swing.BorderFactory.createTitledBorder("Types"));
        panTypes.setLayout(new java.awt.GridLayout(0, 1));

        cbxFilterNull.setSelected(true);
        cbxFilterNull.setText("<none>");
        panTypes.add(cbxFilterNull);

        cbxFilterObj.setSelected(true);
        cbxFilterObj.setText("OBJ");
        panTypes.add(cbxFilterObj);

        cbxFilterCleanup.setSelected(true);
        cbxFilterCleanup.setText("CLEANUP");
        panTypes.add(cbxFilterCleanup);

        cbxFilterWrite.setSelected(true);
        cbxFilterWrite.setText("WRITE");
        panTypes.add(cbxFilterWrite);

        cbxFilterReadWrite.setSelected(true);
        cbxFilterReadWrite.setText("READ/WRITE");
        panTypes.add(cbxFilterReadWrite);

        cbxFilterStartingLogin.setSelected(true);
        cbxFilterStartingLogin.setText("STARTING LOGIN");
        panTypes.add(cbxFilterStartingLogin);

        cbxFilterLoginAttempt.setSelected(true);
        cbxFilterLoginAttempt.setText("LOGIN ATTEMPT");
        panTypes.add(cbxFilterLoginAttempt);

        cbxFilterLoginLoaded.setSelected(true);
        cbxFilterLoginLoaded.setText("LOGIN LOADED");
        panTypes.add(cbxFilterLoginLoaded);

        cbxFilterLoginPublishing.setSelected(true);
        cbxFilterLoginPublishing.setText("LOGIN PUBLISHING");
        panTypes.add(cbxFilterLoginPublishing);

        cbxFilterDisconnectStartI.setSelected(true);
        cbxFilterDisconnectStartI.setText("DISCONNECT START (i)");
        panTypes.add(cbxFilterDisconnectStartI);

        cbxFilterHive.setSelected(true);
        cbxFilterHive.setText("HIVE");
        panTypes.add(cbxFilterHive);

        cbxFilterPdeath.setSelected(true);
        cbxFilterPdeath.setText("PDEATH");
        panTypes.add(cbxFilterPdeath);

        cbxFilterDelete.setSelected(true);
        cbxFilterDelete.setText("DELETE");
        panTypes.add(cbxFilterDelete);

        cbxFilterLocalityEvent.setSelected(true);
        cbxFilterLocalityEvent.setText("Locality Event");
        panTypes.add(cbxFilterLocalityEvent);

        cbxFilterError.setSelected(true);
        cbxFilterError.setText("ERROR");
        panTypes.add(cbxFilterError);

        cbxFilterDwDebugFps.setSelected(true);
        cbxFilterDwDebugFps.setText("DW_DEBUG FPS");
        panTypes.add(cbxFilterDwDebugFps);

        cbxFilterSecondHandZombieInitialized.setSelected(true);
        cbxFilterSecondHandZombieInitialized.setText("SECOND HAND ZOMBIE...");
        panTypes.add(cbxFilterSecondHandZombieInitialized);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panFilter.add(panTypes, gridBagConstraints);

        jLabel1.setText("Content:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panFilter.add(jLabel1, gridBagConstraints);

        panPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder("Players (not yet implemented)"));
        panPlayers.setEnabled(false);
        panPlayers.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMaximumSize(new java.awt.Dimension(150, 130));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 130));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 130));

        jList1.setModel(new DefaultListModel());
        jList1.setEnabled(false);
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panPlayers.add(jScrollPane1, gridBagConstraints);

        cbFilterPlayers.setText("filter selected");
        cbFilterPlayers.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        panPlayers.add(cbFilterPlayers, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panFilter.add(panPlayers, gridBagConstraints);

        btnFilterApply.setText("apply filter to all rows");
        btnFilterApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterApplyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panFilter.add(btnFilterApply, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panRight.add(panFilter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panMain.add(panRight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(panMain, gridBagConstraints);

        menMain.setText("File");

        mniLoadRpt.setText("Load RPT");
        mniLoadRpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLoadRptActionPerformed(evt);
            }
        });
        menMain.add(mniLoadRpt);

        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        menMain.add(mniExit);

        mnbMain.add(menMain);

        setJMenuBar(mnbMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniLoadRptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLoadRptActionPerformed
        final JFileChooser fc = new JFileChooser(rptFile);
        final FileNameExtensionFilter rptFilter = new FileNameExtensionFilter("RPT file", "rpt");
        fc.setFileFilter(rptFilter);
        fc.setDialogTitle("choose RPT file");
        final int returnVal = fc.showOpenDialog(this);

        tgbResume.setEnabled(true);
        tgbResume.setSelected(true);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            rptFile = fc.getSelectedFile();
            model.clear();

            Backend.getInstance().startNewRptTailer(rptFile, new RptTailerListenerImpl());
        }
    }//GEN-LAST:event_mniLoadRptActionPerformed

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCopyActionPerformed
        if (popLine != null) {
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            final Transferable transferable = new StringSelection(popLine.getRawLine());
            clipboard.setContents(transferable, null);
        }
    }//GEN-LAST:event_mniCopyActionPerformed

    private void tgbResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbResumeActionPerformed
        Backend.getInstance().getRptTailer().setPause(!tgbResume.isSelected());
    }//GEN-LAST:event_tgbResumeActionPerformed

    private void tgbAutoScrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbAutoScrollActionPerformed
        autoscroll = tgbAutoScroll.isSelected();
    }//GEN-LAST:event_tgbAutoScrollActionPerformed

    private void btnFilterApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterApplyActionPerformed
        sorter.allRowsChanged();
        tblLines.revalidate();
    }//GEN-LAST:event_btnFilterApplyActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilterApply;
    private javax.swing.JCheckBox cbFilterPlayers;
    private javax.swing.JCheckBox cbxFilterCleanup;
    private javax.swing.JCheckBox cbxFilterDelete;
    private javax.swing.JCheckBox cbxFilterDisconnectStartI;
    private javax.swing.JCheckBox cbxFilterDwDebugFps;
    private javax.swing.JCheckBox cbxFilterError;
    private javax.swing.JCheckBox cbxFilterHive;
    private javax.swing.JCheckBox cbxFilterLocalityEvent;
    private javax.swing.JCheckBox cbxFilterLoginAttempt;
    private javax.swing.JCheckBox cbxFilterLoginLoaded;
    private javax.swing.JCheckBox cbxFilterLoginPublishing;
    private javax.swing.JCheckBox cbxFilterNull;
    private javax.swing.JCheckBox cbxFilterObj;
    private javax.swing.JCheckBox cbxFilterPdeath;
    private javax.swing.JCheckBox cbxFilterReadWrite;
    private javax.swing.JCheckBox cbxFilterSecondHandZombieInitialized;
    private javax.swing.JCheckBox cbxFilterStartingLogin;
    private javax.swing.JCheckBox cbxFilterWrite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menMain;
    private javax.swing.JMenuBar mnbMain;
    private javax.swing.JMenuItem mniCopy;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JMenuItem mniLoadRpt;
    private javax.swing.JPanel panFilter;
    private javax.swing.JPanel panInfo;
    private javax.swing.JPanel panMain;
    private javax.swing.JPanel panPlayers;
    private javax.swing.JPanel panProgress;
    private javax.swing.JPanel panRight;
    private javax.swing.JPanel panTable;
    private javax.swing.JPanel panTypes;
    private javax.swing.JProgressBar pgbLines;
    private javax.swing.JPopupMenu pumTableitem;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblLines;
    private javax.swing.JToggleButton tgbAutoScroll;
    private javax.swing.JToggleButton tgbResume;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables

    static class RptTableDateCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            final JComponent component;
            if (value instanceof Date) {
                component = (JComponent) super.getTableCellRendererComponent(table, new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(value), isSelected, hasFocus, row, column);
            } else {
                component = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            return component;
        }
    }

    class RptTailerListenerImpl implements RptTailerListener {

        @Override
        public void rptLinesTailed(final List<AbstractRptLine> rptLines) {
            if (rptLines != null) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        model.addLines(rptLines);
                        pgbLines.setString(model.getRowCount() + " lines (" + sorter.getViewRowCount() + " filtered)");
                        if (autoscroll) {
                            try {
                                tblLines.scrollRectToVisible(tblLines.getCellRect(tblLines.getRowCount() - 1, tblLines.getColumnCount(), true));
                            } catch (final IndexOutOfBoundsException exception) {
                                LOG.warn("Error while doing autoscroll.", exception);
                            }
                        }
                    }
                });
            }
        }

        @Override
        public void tailingResumed() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pgbLines.setEnabled(true);
                }
            });
        }

        @Override
        public void tailingWait() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pgbLines.setEnabled(false);
                }
            });
        }

        @Override
        public void tailingStarted(final long byteLength) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pgbLines.setMaximum(new Long(byteLength).intValue());
                }
            });
        }

        @Override
        public void tailingProceed(final long byteRead) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pgbLines.setValue(new Long(byteRead).intValue());
                }
            });
        }

        @Override
        public void tailingStopped() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pgbLines.setEnabled(false);
                    pgbLines.setMaximum(0);
                    pgbLines.setValue(0);
                }
            });
        }
    }

    class RowFilterImpl extends RowFilter<RptLineTableModel, Integer> {

        @Override
        public boolean include(Entry<? extends RptLineTableModel, ? extends Integer> entry) {
            final RptLineTableModel model = entry.getModel();
            final AbstractRptLine line = model.getLine(entry.getIdentifier());

            final String contentFilter = txtFilter.getText();
            final boolean contentFilterIncludes;
            if (contentFilter != null) {
                final Pattern pattern = Pattern.compile(contentFilter);
                contentFilterIncludes = pattern.matcher(line.getRawContent()).find();
            } else {
                contentFilterIncludes = true;
            }

            final boolean typeFilterIncludes;
            if (line.getType() == null) {
                typeFilterIncludes = cbxFilterNull.isSelected();
            } else {
                switch (line.getType()) {
                    case CLEANUP:
                        typeFilterIncludes = cbxFilterCleanup.isSelected();
                        break;
                    case DELETE:
                        typeFilterIncludes = cbxFilterDelete.isSelected();
                        break;
                    case DISCONNECT_START_I:
                        typeFilterIncludes = cbxFilterDisconnectStartI.isSelected();
                        break;
                    case ERROR:
                        typeFilterIncludes = cbxFilterError.isSelected();
                        break;
                    case HIVE:
                        typeFilterIncludes = cbxFilterHive.isSelected();
                        break;
                    case LOCALITY_EVENT:
                        typeFilterIncludes = cbxFilterLocalityEvent.isSelected();
                        break;
                    case LOGIN_ATTEMPT:
                        typeFilterIncludes = cbxFilterLoginAttempt.isSelected();
                        break;
                    case LOGIN_LOADED:
                        typeFilterIncludes = cbxFilterLoginLoaded.isSelected();
                        break;
                    case LOGIN_PUBLISHING:
                        typeFilterIncludes = cbxFilterLoginPublishing.isSelected();
                        break;
                    case OBJ:
                        typeFilterIncludes = cbxFilterObj.isSelected();
                        break;
                    case PDEATH:
                        typeFilterIncludes = cbxFilterPdeath.isSelected();
                        break;
                    case READ_WRITE:
                        typeFilterIncludes = cbxFilterReadWrite.isSelected();
                        break;
                    case STARTING_LOGIN:
                        typeFilterIncludes = cbxFilterStartingLogin.isSelected();
                        break;
                    case WRITE:
                        typeFilterIncludes = cbxFilterWrite.isSelected();
                        break;
                    case DW_DEBUG_FPS:
                        typeFilterIncludes = cbxFilterDwDebugFps.isSelected();
                        break;
                    case SECOND_HAND_ZOMBIE_INITIALIZED:
                        typeFilterIncludes = cbxFilterSecondHandZombieInitialized.isSelected();
                        break;
                    default:
                        typeFilterIncludes = true;
                }
            }

            return contentFilterIncludes && typeFilterIncludes;
        }
    }

    class RptTableMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(final MouseEvent event) {

            if (event.isPopupTrigger()) {
                final int row = tblLines.rowAtPoint(event.getPoint());
                if (row >= 0 && row < tblLines.getRowCount()) {
                    tblLines.setRowSelectionInterval(row, row);
                } else {
                    tblLines.clearSelection();
                }

                int rowindex = tblLines.getSelectedRow();

                if (rowindex >= 0 && event.isPopupTrigger() && event.getComponent() instanceof JTable) {
                    popLine = (AbstractRptLine) model.getLine(tblLines.convertRowIndexToModel(row));
                    pumTableitem.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        }
    }

    class RptTableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(final ListSelectionEvent event) {
            panInfo.removeAll();
            final int row = tblLines.getSelectedRow();
            if (row >= 0) {
                final AbstractRptLine rptLine = model.getLine(tblLines.convertRowIndexToModel(row));
                final AbstractRptLineRenderer renderer = RptLineRendererProvider.getRenderer(rptLine.getType());
                renderer.setRptLine(rptLine);
                panInfo.add(renderer);
            }
            panInfo.validate();
            panInfo.repaint();
        }
    }

    static class CorrectLayoutJTable extends JTable {

        @Override
        public void doLayout() {
            if (getTableHeader().getResizingColumn() == null) {
                if (getAutoResizeMode() == JTable.AUTO_RESIZE_LAST_COLUMN) {
                    getTableHeader().setResizingColumn(getColumnModel().getColumn(getColumnModel().getColumnCount() - 1));
                }
            }
            super.doLayout();
        }
    }
}