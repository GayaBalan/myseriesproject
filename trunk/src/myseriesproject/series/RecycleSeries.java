/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RestoreSeries.java
 *
 * Created on 14 Ιουν 2010, 1:26:09 μμ
 */
package myseriesproject.series;

import database.DBHelper;
import database.SeriesRecord;
import help.HelpWindow;
import java.sql.SQLException;
import java.util.ArrayList;
import tools.MySeriesLogger;
import java.util.Iterator;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import myComponents.MyMessages;
import myComponents.myEvents.MyEvent;
import myComponents.myEvents.MyEventHandler;
import myComponents.myEvents.MyEventsClass;
import myComponents.myGUI.MyDraggable;
import myComponents.myTableCellRenderers.MyCheckBoxCellRenderer;
import myseriesproject.MySeries;

/**
 *
 * @author ssoldatos
 */
public class RecycleSeries extends MyDraggable {

  public static final long serialVersionUID = 23534663L;
  private final ArrayList<SeriesRecord> series;
  MyEventsClass evClass;
  private MySeries m;
  public static final int COLUMN_SERIES = 0;
  public static final int COLUMN_RESTORE = 1;
  public static final int COLUMN_DELETE = 2;

  /** Creates new form RestoreSeries */
  public RecycleSeries(MySeries m) {
    this(new ArrayList<SeriesRecord>(), m);
  }

  public RecycleSeries(ArrayList<SeriesRecord> series, MySeries m) {
    this.m = m;
    evClass = new MyEventsClass(m);
    myseriesproject.MySeries.glassPane.activate(null);
    MySeriesLogger.logger.log(Level.INFO, "Initializing components");
    initComponents();
    MySeriesLogger.logger.log(Level.FINE, "Components initialized");
    table.getColumnModel().getColumn(COLUMN_SERIES).setPreferredWidth(400);
    table.getColumnModel().getColumn(COLUMN_RESTORE).setPreferredWidth(100);
    table.getColumnModel().getColumn(COLUMN_RESTORE).setCellRenderer(new MyCheckBoxCellRenderer());
    table.getColumnModel().getColumn(COLUMN_DELETE).setPreferredWidth(100);
    table.getColumnModel().getColumn(COLUMN_DELETE).setCellRenderer(new MyCheckBoxCellRenderer(MyCheckBoxCellRenderer.DELETED_CHECK));
    this.series = series;
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    MySeriesLogger.logger.log(Level.INFO, "Filling table with deleted series");
    for (Iterator<SeriesRecord> it = series.iterator(); it.hasNext();) {
      SeriesRecord seriesRecord = it.next();
      model.addRow(new Object[]{seriesRecord, false, false});
    }
    table.setModel(model);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        bt_cancel = new myComponents.myGUI.buttons.MyButtonCancel();
        bt_ok = new myComponents.myGUI.buttons.MyButtonOk();
        bt_help = new myComponents.myGUI.buttons.MyButtonHelp();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        title.setFont(title.getFont().deriveFont(title.getFont().getStyle() | java.awt.Font.BOLD, title.getFont().getSize()+2));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Recycle Bin");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Series", "Restore", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scroll.setViewportView(table);

        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        bt_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_okActionPerformed(evt);
            }
        });

        bt_help.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        bt_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_helpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(bt_ok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_help, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_help, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void bt_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_helpActionPerformed
    MySeriesLogger.logger.log(Level.INFO, "Showing help window");
    new HelpWindow(HelpWindow.RECYCLE_BIN);
  }//GEN-LAST:event_bt_helpActionPerformed

  private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
    MySeriesLogger.logger.log(Level.INFO, "Restoring series aborted by the user");
    dispose();
    myseriesproject.MySeries.glassPane.deactivate();
  }//GEN-LAST:event_bt_cancelActionPerformed

  private int restore(ArrayList<SeriesRecord> series) {
    int count = 0;
    for (Iterator<SeriesRecord> it = series.iterator(); it.hasNext();) {
      SeriesRecord ser = it.next();
      try {
        ser.setDeleted(0);
        ser.save();
        count++;
        MySeriesLogger.logger.log(Level.FINE, "Restored series {0}", ser.getFullTitle());
      } catch (SQLException ex) {
        MySeriesLogger.logger.log(Level.SEVERE, "Sql exception occured", ex);
        return count;
      }
    }
    return count;
  }

  private int delete(ArrayList<SeriesRecord> series) {
    int count = 0;
    for (Iterator<SeriesRecord> it = series.iterator(); it.hasNext();) {
      SeriesRecord ser = it.next();
      try {
        //System.out.println("Deleting ser " + ser.getFullTitle());
        ser.delete();
        count++;
        MySeriesLogger.logger.log(Level.FINE, "Deleted series {0}", ser.getFullTitle());
      } catch (SQLException ex) {
        MySeriesLogger.logger.log(Level.SEVERE, "Sql exception occured", ex);
        return count;
      }
    }
    return count;
  }

  private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
    TableModel model = table.getModel();
    int deletes = 0, restores = 0;
    ArrayList<SeriesRecord> restoreList = new ArrayList<SeriesRecord>();
    ArrayList<SeriesRecord> deleteList = new ArrayList<SeriesRecord>();
    for (int i = 0; i < model.getRowCount(); i++) {
      if ((Boolean) model.getValueAt(i, COLUMN_RESTORE)) {
        SeriesRecord ser = (SeriesRecord) model.getValueAt(i, 0);
        restoreList.add(ser);
      }
      if ((Boolean) model.getValueAt(i, COLUMN_DELETE)) {
        SeriesRecord ser = (SeriesRecord) model.getValueAt(i, 0);
        deleteList.add(ser);
      }
    }
    if (!restoreList.isEmpty()) {
      restores = restore(restoreList);
    }
    if (!deleteList.isEmpty()) {
      setVisible(false);
      int ans = MyMessages.confirm("Delete Series", "Are you sure that you want to delete the following series?\n"
              + deleteList.toString().replaceAll("[,\\[\\]]", "\n")
              + "\nThis action can't be undone", true);
      
      if(ans==JOptionPane.YES_OPTION){
        deletes = delete(deleteList);
      } else {
        setVisible(true);
        return;
      }
    }
    
    if (restores > 0) {
      evClass.fireMyEvent(new MyEvent(this, MyEventHandler.SERIES_UPDATE));
    }
    dispose();
    myseriesproject.MySeries.glassPane.deactivate();
  }//GEN-LAST:event_bt_okActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private myComponents.myGUI.buttons.MyButtonCancel bt_cancel;
    private myComponents.myGUI.buttons.MyButtonHelp bt_help;
    private myComponents.myGUI.buttons.MyButtonOk bt_ok;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
