/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InternetUpdate.java
 *
 * Created on 8 Ιαν 2009, 9:26:04 μμ
 */
package tools.internetUpdate;

import tools.internetUpdate.epguides.EgUpdate;
import tools.internetUpdate.tvrage.TrUpdate;
import database.SeriesRecord;
import java.util.Vector;
import java.util.logging.Level;
import tools.MySeriesLogger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import myComponents.MyMessages;
import myComponents.myGUI.MyDraggable;
import myseries.MySeries;
import tools.Skin;

/**
 * The internet update form
 * @author lordovol
 */
public class InternetUpdate extends MyDraggable {

    /** Tv rage name : "TvRage"   */
    public static final String TV_RAGE_NAME = "TvRage";
    /** EpGuides name : "EpGuides"   */
    public static final String EP_GUIDES_NAME = "EpGuides";
    /** The epguides url : "http://www.epguides.com/" **/
    public static final String EP_GUIDES_URL = "http://www.epguides.com/";
    /** The tvrage url : "http://www.tvrage.com **/
    public static final String TV_RAGE_URL = "http://www.tvrage.com/";
    /** The search show rss url : "http://services.tvrage.com/feeds/search.php?show=" **/
    public static final String TV_RAGE_SEARCH_SHOW_URL = "http://services.tvrage.com/feeds/search.php?show=";
    /** The tvrage epoisodes list rss url : "http://services.tvrage.com/feeds/episode_list.php?sid=" **/
    public static final String TV_RAGE_EPISODE_LIST_URL = "http://services.tvrage.com/feeds/episode_list.php?sid=";
    /** tvrage images url : "http://images.tvrage.com/"; */
    public static String TV_RAGE_IMAGES_URL = "http://images.tvrage.com/";
    /**
     * If proccess is finished
     */
    public boolean finished = false;
    private MySeries m;
    private Thread t;
    private SeriesRecord currentSeries = null;
    private static final long serialVersionUID = 4364575758658L;
    private String site = "";

    /** Creates new form InternetUpdate from MySeries Form
     * @param m The myseries form
     * @param site From which site to update
     */
    public InternetUpdate(MySeries m, String site) {
        this.m = m;
        this.site = site;
        MySeriesLogger.logger.log(Level.INFO, "Internet update from {0} for all series", site);
        MySeriesLogger.logger.log(Level.INFO, "Initializong components");
        initComponents();
        MySeriesLogger.logger.log(Level.FINE, "Components initialized");
        startUpdate(m.tableEpisodes);
        setLocationRelativeTo(m);
        setVisible(true);

    }

    /**
     * Constructs an internet update for a specific series
     * @param m The mySeries form
     * @param currentSeries The series to update
     * @param site From which site to update
     */
    public InternetUpdate(MySeries m, SeriesRecord currentSeries, String site) {
        this.m = m;
        this.currentSeries = currentSeries;
        this.site = site;
        MySeriesLogger.logger.log(Level.INFO, "Internet update from {0} for series {1}", new Object[]{site, currentSeries.getFullTitle()});
        MySeriesLogger.logger.log(Level.INFO, "Initializong components");
        initComponents();
        MySeriesLogger.logger.log(Level.FINE, "Components initialized");
        startUpdate(m.tableEpisodes);
        setLocationRelativeTo(m);
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

    jPanel2 = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    label_update_series = new javax.swing.JLabel();
    progress_bar = new javax.swing.JProgressBar();
    jScrollPane1 = new javax.swing.JScrollPane();
    editor_messages = new javax.swing.JEditorPane();
    jLabel1 = new javax.swing.JLabel();
    bt_cancel = new myComponents.myGUI.buttons.MyButtonCancel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel1.setOpaque(false);

    progress_bar.setStringPainted(true);

    editor_messages.setContentType("text/html");
    jScrollPane1.setViewportView(editor_messages);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(progress_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
            .addGap(14, 14, 14))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
              .addComponent(label_update_series, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
            .addContainerGap())))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(progress_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(label_update_series, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        .addContainerGap())
    );

    jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+2));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("MySeries - Internet Update");

    bt_cancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bt_cancelActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(10, 10, 10))
          .addComponent(bt_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(29, 29, 29)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
            .addGap(37, 37, 37))))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void startUpdate(JTable episodesTable) {
        Runnable task = null;
        MySeriesLogger.logger.log(Level.INFO, "Satring update");
        if (site.equals(EP_GUIDES_NAME)) {
            task = new EgUpdate(this,episodesTable);
        } else if (site.equals(TV_RAGE_NAME)) {
            task = new TrUpdate(this,episodesTable);
        } else {
            return;
        }
        t = new Thread(task);
        t.start();
    }

  private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
      if (t == null) {
          MySeries.glassPane.deactivate();
          MySeriesLogger.logger.log(Level.INFO, "Updating canceled by the user");
          dispose();
      } else {

          if (finished) {
              MySeries.glassPane.deactivate();
              MySeriesLogger.logger.log(Level.INFO, "Updating canceled by the user");
              dispose();
          } else {
              MySeriesLogger.logger.log(Level.INFO, "Canceling while updating");
              int i = MyMessages.confirm("Abort?", "Do you want to cancel the update?", true);
              if (i == JOptionPane.OK_OPTION) {
                  MySeriesLogger.logger.log(Level.INFO, "Updating canceled by the user");
                  t.interrupt();
                  MySeries.glassPane.deactivate();
                  dispose();
              } else {
                  MySeriesLogger.logger.log(Level.INFO, "Canceling aborted");
              }
          }
      }
  }//GEN-LAST:event_bt_cancelActionPerformed

    /**
     * @return the currentSeries
     */
    public SeriesRecord getCurrentSeries() {
        return currentSeries;
    }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private myComponents.myGUI.buttons.MyButtonCancel bt_cancel;
  javax.swing.JEditorPane editor_messages;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  public javax.swing.JLabel label_update_series;
  public javax.swing.JProgressBar progress_bar;
  // End of variables declaration//GEN-END:variables
}
