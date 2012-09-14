/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.rpttool.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.RowFilter.Entry;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import org.coolcow.rpttool.backend.RptLine;
import org.coolcow.rpttool.backend.RptTailer;
import org.coolcow.rpttool.backend.RptTailerListener;

/**
 *
 * @author jruiz
 */
public class MainFrame extends javax.swing.JFrame {

    public final static DateFormat DATE_FORMAT = new SimpleDateFormat("kk:mm:ss");
    private static MainFrame INSTANCE;
    
    private final RptLineTableModel model = new RptLineTableModel();
    private File rptFile = null;
    private int lineNumber = 0;
    private RptTailer rptFileTailer;
    private RptLine popLine = null;

    /**
     * Creates new form NewJFrame
     */
    private MainFrame() {
        initComponents();

        tblLines.setAutoCreateRowSorter(false);

        final TableRowSorter<RptLineTableModel> sorter = new TableRowSorter<>(model);
        tblLines.setRowSorter(sorter);

        final RowFilter complexFilter = new RowFilter<RptLineTableModel, Integer>() {

            @Override
            public boolean include(Entry<? extends RptLineTableModel, ? extends Integer> entry) {
                RptLineTableModel model = entry.getModel();
                RptLine line = model.getLine(entry.getIdentifier());

                final String contentFilter = txtFilter.getText();
                final boolean contentFilterIncludes;
                if (contentFilter != null) {
                    final Pattern pattern = Pattern.compile(contentFilter);
                    contentFilterIncludes = pattern.matcher(line.getContent()).find();
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
                        case DISCONNECT_START:
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
        };

        sorter.setRowFilter(complexFilter);

        tblLines.getColumnModel().getColumn(RptLineTableModel.COLUMN_NUMBER).setPreferredWidth(60);
        tblLines.getColumnModel().getColumn(RptLineTableModel.COLUMN_TIME).setPreferredWidth(60);

        tblLines.setDefaultRenderer(Date.class, new RptLineTableDateCellRenderer());

        tblLines.addMouseListener(new MouseAdapter() {

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
                    
                    if (rowindex >= 0 && event.isPopupTrigger() && event.getComponent() instanceof JTable ) {
                        popLine = (RptLine) model.getLine(tblLines.convertRowIndexToModel(row));
                        pumTableitem.show(event.getComponent(), event.getX(), event.getY());
                    }
                }
            }
        });
    }

    public static MainFrame getInstance() {
        if (INSTANCE == null) {
            return (INSTANCE = new MainFrame());
        } else {
            return INSTANCE;
        }
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
        cbAutoscroll = new javax.swing.JCheckBox();
        btnPauseResume = new javax.swing.JButton();
        panTable = new javax.swing.JPanel();
        scrTable = new javax.swing.JScrollPane();
        tblLines = new javax.swing.JTable() {
            @Override public void doLayout() {
                if (getTableHeader().getResizingColumn() == null) {
                    if (getAutoResizeMode() == JTable.AUTO_RESIZE_LAST_COLUMN) {
                        getTableHeader().setResizingColumn(getColumnModel().getColumn(getColumnModel().getColumnCount()-1));
                    }
                }
                super.doLayout();
            }
        };
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
        mnbMain = new javax.swing.JMenuBar();
        menMain = new javax.swing.JMenu();
        mniLoadRpt = new javax.swing.JMenuItem();
        mniExit = new javax.swing.JMenuItem();

        mniCopy.setText("copy to clipboard");
        mniCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCopyActionPerformed(evt);
            }
        });
        pumTableitem.add(mniCopy);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RPT Tool (alpha) - by coolcow");
        setMinimumSize(new java.awt.Dimension(400, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panMain.setLayout(new java.awt.GridBagLayout());

        panProgress.setLayout(new java.awt.GridBagLayout());

        pgbLines.setPreferredSize(new java.awt.Dimension(146, 20));
        pgbLines.setString("0 lines");
        pgbLines.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panProgress.add(pgbLines, gridBagConstraints);

        cbAutoscroll.setSelected(true);
        cbAutoscroll.setToolTipText("(dis-)activate autoscroll");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panProgress.add(cbAutoscroll, gridBagConstraints);

        btnPauseResume.setText("pause");
        btnPauseResume.setEnabled(false);
        btnPauseResume.setMaximumSize(new java.awt.Dimension(75, 23));
        btnPauseResume.setMinimumSize(new java.awt.Dimension(75, 23));
        btnPauseResume.setPreferredSize(new java.awt.Dimension(75, 23));
        btnPauseResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseResumeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panProgress.add(btnPauseResume, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
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
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        panMain.add(panTable, gridBagConstraints);
        panTable.getAccessibleContext().setAccessibleName("");

        panRight.setLayout(new java.awt.GridBagLayout());

        panInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panInfo.setLayout(new java.awt.GridBagLayout());
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

        panPlayers.setBorder(javax.swing.BorderFactory.createTitledBorder("Players"));
        panPlayers.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMaximumSize(new java.awt.Dimension(150, 130));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 130));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 130));

        jList1.setModel(new DefaultListModel());
        jList1.setEnabled(false);
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panPlayers.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panFilter.add(panPlayers, gridBagConstraints);

        cbFilterPlayers.setText("selected players");
        cbFilterPlayers.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panFilter.add(cbFilterPlayers, gridBagConstraints);

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

        btnPauseResume.setEnabled(true);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            rptFile = fc.getSelectedFile();
            new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    loadLines(rptFile);
                    return null;
                }
            }.execute();
        }
    }//GEN-LAST:event_mniLoadRptActionPerformed

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCopyActionPerformed
        if (popLine != null) {
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            final Transferable transferable = new StringSelection(popLine.getContent());
            clipboard.setContents(transferable, null);
        }
    }//GEN-LAST:event_mniCopyActionPerformed

    private void btnPauseResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseResumeActionPerformed
        if (rptFileTailer.isPause()) {
            rptFileTailer.setPause(false);
            btnPauseResume.setText("resume");
        } else {
            rptFileTailer.setPause(true);
            btnPauseResume.setText("pause");
        }
    }//GEN-LAST:event_btnPauseResumeActionPerformed

    private void loadLines(final File rptFile) {
        model.clear();
        lineNumber = 0;
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                pgbLines.setIndeterminate(true);
                pgbLines.setEnabled(true);
            }
        });

        if (rptFileTailer != null) {
            rptFileTailer.stopTailing();
        }
        rptFileTailer = new RptTailer(rptFile, 1000);
        rptFileTailer.addLogFileTailerListener(
                new RptTailerListener() {

                    @Override
                    public void newLineTailed(final String lineString) {
                        lineNumber++;
                        EventQueue.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                pgbLines.setValue(lineNumber);
                                pgbLines.setString(lineNumber + " lines (" + tblLines.getRowCount() + " filtered)");
                            }
                        });

                        try {
                            final RptLine line = RptLine.parseLine(lineNumber, lineString);
                            if (line != null) {
                                model.addLine(line);
                            }
                        } catch (final Exception ex) {
                            ex.printStackTrace();
                        }
                        if (cbAutoscroll.isSelected()) {
                            EventQueue.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    tblLines.scrollRectToVisible(tblLines.getCellRect(tblLines.getRowCount() - 1, tblLines.getColumnCount(), true));
                                }
                            });
                        }
                    }

                    @Override
                    public void tailingStarted() {
                        btnPauseResume.setEnabled(false);
                        btnPauseResume.setText("loading");
                    }

                    @Override
                    public void tailingFinished() {
                        btnPauseResume.setEnabled(true);
                        btnPauseResume.setText("pause");
                    }
                });
        rptFileTailer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                final MainFrame frame = new MainFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPauseResume;
    private javax.swing.JCheckBox cbAutoscroll;
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
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables

    class RptLineTableDateCellRenderer extends DefaultTableCellRenderer {
 
        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            final JComponent component;
            if (value instanceof Date) {
                component = (JComponent) super.getTableCellRendererComponent(table, MainFrame.DATE_FORMAT.format(value), isSelected, hasFocus, row, column);
            } else {
                component = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            return component;
        }
    
    }

}
