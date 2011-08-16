/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GetTvRageID.java
 *
 * Created on 21 Φεβ 2010, 12:18:09 μμ
 */
package tools.internetUpdate.tvrage;

import database.SeriesRecord;
import java.awt.Dialog.ModalityType;
import tools.MySeriesLogger;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import myComponents.MyMessages;
import myComponents.MyUsefulFunctions;
import myComponents.myEvents.MyEvent;
import myComponents.myEvents.MyEventHandler;
import myComponents.myEvents.MyEventsClass;
import myComponents.myGUI.MyDraggable;
import myseriesproject.MySeries;
import myseriesproject.series.AdminSeries;
import myseriesproject.series.Series;
import tools.Skin;
import tools.download.screenshot.DownloadScreenshot;
import tools.internetUpdate.InternetUpdate;
import tools.internetUpdate.tvrage.SearchTvRage.TvRageSeries;

/**
 * Get TvRage id form
 * @author lordovol
 */
public class TrGetId extends MyDraggable {

  private MyEventsClass evClass;
  private MySeries m;
  private int series_ID = 0;
  private AdminSeries adminSeries;
  String title;
  ComboBoxModel resultsModel = new DefaultComboBoxModel();
  public int tvRageID = 0;
  private boolean isConected;
  private static final long serialVersionUID = 345645747547L;
  private static int instances = 0;
  private boolean cancel = true;
  private boolean screenshot = false;
  private boolean downloadScreenshot;
  public DownloadScreenshot scrDownload;

  {
    isConected = MyUsefulFunctions.hasInternetConnection(InternetUpdate.TV_RAGE_URL);
  }
  

  /** Creates new form GetTvRageID */
  public TrGetId() {
  }

  /**
   * Creates a tvrage get id from My series form
   * @param m MySeries form
   * @param series_ID The series ID
   * @param title The series title
   * @param internetUpdate If internet update should invoked
   */
  public TrGetId(MySeries m, int series_ID, String title, boolean downloadScreenshot) {
    MySeriesLogger.logger.log(Level.INFO, "Getting tvrage id for series {0} from application", title);
    this.m = m;
    this.series_ID = series_ID;
    this.title = title;
    this.downloadScreenshot = downloadScreenshot;
    evClass = new MyEventsClass(m);
    getID();


  }

  /**
   * Creates a TvRage get id from admin series form
   * @param adminSeries The ad
   * @param title
   */
  public TrGetId(AdminSeries adminSeries, String title) {
    MySeriesLogger.logger.log(Level.INFO, "Getting tvrage id for series {0} from series admin", title);
    this.adminSeries = adminSeries;
    this.title = title;
    getID();
    adminSeries.setModalityType(ModalityType.APPLICATION_MODAL);
  }

  /**
   * Creates a TvRage get id from admin series form
   * @param adminSeries The ad
   * @param title
   * @param screenshot If screenshot is queried
   */
  public TrGetId(AdminSeries adminSeries, String title, boolean screenshot) {
    MySeriesLogger.logger.log(Level.INFO, "Getting tvrage id for series {0} for screenshot", title);
    this.adminSeries = adminSeries;
    this.title = title;
    this.screenshot = screenshot;
    getID();
    adminSeries.setModalityType(ModalityType.APPLICATION_MODAL);

  }

  private void getID() {
    if (!isConected) {
      MySeriesLogger.logger.log(Level.WARNING, "Could not connect to internet");
      MyMessages.internetError();
      bt_cancelActionPerformed(null);
      return;
    }
    MySeriesLogger.logger.log(Level.INFO, "Initializong components");
    initComponents();
    MySeriesLogger.logger.log(Level.FINE, "Components initialized");
    label_title.setText("Select the right series from the results found for " + title);
    setLocationRelativeTo(null);
    setLocation(getX(), 200);
    setModalityType(ModalityType.MODELESS);
    pack();
    setVisible(true);
    MySeriesLogger.logger.log(Level.INFO, "Searching for series {0}", title);
    SearchTvRage s = new SearchTvRage(title, this);
    Thread t = new Thread(s);
    t.start();
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
    label_title = new javax.swing.JLabel();
    progress = new javax.swing.JProgressBar();
    combo_results = new javax.swing.JComboBox();
    jLabel2 = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    bt_cancel = new myComponents.myGUI.buttons.MyButtonCancel();
    bt_ok = new myComponents.myGUI.buttons.MyButtonOk();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jPanel1.setBackground(Skin.getInnerColor());
    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    label_title.setText("jLabel2");

    progress.setString("");
    progress.setStringPainted(true);

    combo_results.setModel(resultsModel);

    jLabel2.setText("Results");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(combo_results, 0, 329, Short.MAX_VALUE))
          .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
          .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(11, 11, 11)
        .addComponent(label_title)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel2)
          .addComponent(combo_results, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+2));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Internet Update");

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

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addComponent(jLabel1)
          .addComponent(bt_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(12, 12, 12)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
    //MySeries.glassPane.deactivate();
    if (!cancel) {
      if (adminSeries != null) {
        adminSeries.setModalityType(ModalityType.APPLICATION_MODAL);
        if (tvRageID > 0) {
          adminSeries.textfield_tvRageID.setText(String.valueOf(tvRageID));
          if (screenshot) {
            setVisible(false);
            DownloadScreenshot g = new DownloadScreenshot(tvRageID, title);
            dispose();
            if (g.isSuccess()) {
              adminSeries.textfield_screenshot.setText(g.getFilename());
              MyMessages.message("Downloading screenshot", "The screenshot was saved in the images folder");
              adminSeries.setVisible(true);
              return;
            } else {
              MyMessages.warning("Downloading screenshot", "No screenshot was found", true);
              adminSeries.setVisible(true);
              return;
            }
          }
        }
        dispose();
        adminSeries.setVisible(true);
      } else {
        if (m != null && !downloadScreenshot) {
          InternetUpdate iu = new InternetUpdate(m, Series.getCurrentSerial(), InternetUpdate.TV_RAGE_NAME);
        } else {
           scrDownload = new DownloadScreenshot(tvRageID, title);
        }
      }
    } else {
      dispose();
      if (adminSeries != null) {
        adminSeries.setVisible(true);
      }

    }
    dispose();
  }//GEN-LAST:event_bt_cancelActionPerformed

  private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
    TvRageSeries sel = (TvRageSeries) combo_results.getSelectedItem();
    tvRageID = Integer.parseInt(sel.id);
    if (series_ID > 0) {
      try {
        SeriesRecord cSeries = SeriesRecord.queryOne(SeriesRecord.C_SERIES_ID +"=?", 
                    new String[]{String.valueOf(series_ID)},null);
        cSeries.setTvrage_ID(tvRageID);
        cSeries.save();
        MySeriesLogger.logger.log(Level.INFO, "Setting current series to {0}", cSeries.getFullTitle());
        MyEvent event = new MyEvent(m, MyEventHandler.SET_CURRENT_SERIES);
        event.setSeries(cSeries);
        evClass.fireMyEvent(event);
      } catch (SQLException ex) {
        MySeriesLogger.logger.log(Level.SEVERE, null, ex);
        MyMessages.error("SQL Error", "TvRage ID could not be saved in database", true);
      }
    }
    cancel = false;
    bt_cancelActionPerformed(evt);
    //MySeries.glassPane.deactivate();
  }//GEN-LAST:event_bt_okActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private myComponents.myGUI.buttons.MyButtonCancel bt_cancel;
  private myComponents.myGUI.buttons.MyButtonOk bt_ok;
  protected javax.swing.JComboBox combo_results;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JLabel label_title;
  public javax.swing.JProgressBar progress;
  // End of variables declaration//GEN-END:variables
}
