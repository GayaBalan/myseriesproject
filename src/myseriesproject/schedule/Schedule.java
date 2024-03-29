/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Schedule.java
 *
 * Created on 9 Ιουν 2010, 12:03:10 μμ
 */
package myseriesproject.schedule;

import java.awt.Color;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import tools.MySeriesLogger;
import tools.Skin;

/**
 *
 * @author ssoldatos
 */
public class Schedule extends javax.swing.JPanel {

 

  /** Creates new form Schedule */
  public Schedule() {
    initComponents();
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
        lb_title = new javax.swing.JLabel();
        schedule = new com.googlecode.scheduler.Scheduler("db");
        schedule.getTblCalendar().getTableHeader().setBorder(BorderFactory.createLineBorder(Color.GRAY));

        setOpaque(false);

        jPanel1.setOpaque(false);

        lb_title.setFont(lb_title.getFont().deriveFont(lb_title.getFont().getStyle() | java.awt.Font.BOLD, lb_title.getFont().getSize()+2));
        lb_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_title.setText("Schedule");

        schedule.setDatabase("");
        schedule.setTextColor(Skin.getSkinColor());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_title, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_title;
    private com.googlecode.scheduler.Scheduler schedule;
    // End of variables declaration//GEN-END:variables

  /**
   * @return the schedule
   */
  public com.googlecode.scheduler.Scheduler getSchedule() {
    return schedule;
  }

  public void setTextColor(Color color){
      lb_title.setForeground(color);
      schedule.setTextColor(color);
  }
}
