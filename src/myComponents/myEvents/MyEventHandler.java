/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myComponents.myEvents;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myseries.MySeries;
import myseries.episodes.Episodes;
import myseries.series.Series;

/**
 *
 * @author ssoldatos
 */
public class MyEventHandler implements MyEventListener {

  public static final int SERIES_UPDATE = 0;
  public static final int EPISODES_UPDATE = 1;
  public static final int SET_CURRENT_SERIES = 2;

  @Override
  public void myEventOccured(MyEvent evt) {
    MySeries m = null;
    Object source = evt.getSource();
    if (source instanceof myseries.MySeries) {
      m = (MySeries) source;
    }
    try {
      //Add event responses here
      if (evt.getType() == SERIES_UPDATE) {
        Series.updateSeriesTable(false);
      } else if (evt.getType() == SET_CURRENT_SERIES) {
        Series.setCurrentSerial(evt.getSeries());
        MySeries.tableSeries.setRowSelectionInterval(m.getSeriesTableRow(evt.getSeries()),m.getSeriesTableRow(evt.getSeries()));
        Episodes.updateEpisodesTable();
      }
    } catch (SQLException ex) {
      myseries.MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }
}
