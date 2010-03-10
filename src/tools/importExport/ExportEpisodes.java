/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.importExport;

import database.EpisodesRecord;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import myseries.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import myseries.episodes.Episodes;
import myseries.series.Series;

/**
 *
 * @author lordovol
 */
public class ExportEpisodes {

  private File file;

  /**
   * Exports the episodes of the current Series<br />
   * It exports the episode number, the episode title and the date when the episodes was aired
   */
  public ExportEpisodes()  {
    MySeries.logger.log(Level.INFO, "Exporting episodes of " + Series.getCurrentSerial().getFullTitle());
    JFileChooser f = new JFileChooser();
    f.setApproveButtonText("Export");
    f.setDialogTitle("Export episodes of " + Series.getCurrentSerial().getFullTitle());
    f.setFileFilter(new exportFilter());
    f.setSelectedFile(new File(Series.getCurrentSerial().getFullTitle() + ".eef"));
    int returnVal = f.showDialog(null, "Export");
    f.setApproveButtonText("Export");
    if (returnVal == JFileChooser.CANCEL_OPTION) {
      MySeries.logger.log(Level.INFO, "Exporting aborted");
    } else {
      file = f.getSelectedFile();
      MySeries.logger.log(Level.INFO, "Exporting episodes to " + file.getName());
      try {
        exportEpisodesToFile(file);
      } catch (IOException ex) {
        MySeries.logger.log(Level.SEVERE, "Could not write to file", ex);
      } catch (SQLException ex) {
        Logger.getLogger(ExportEpisodes.class.getName()).log(Level.SEVERE, "Could not fetch the episodes from the database", ex);
      }
    }
  }

  /**
   * Writes the file with the episodes
   * @param file The file to write
   * @throws java.io.IOException
   * @throws java.sql.SQLException
   */
  private void exportEpisodesToFile(File file) throws IOException, SQLException {
    PrintWriter out = myComponents.MyUsefulFunctions.createOutputStream(file, false);
    ArrayList<EpisodesRecord> episodes = Episodes.getCurrentSeriesEpisodes();
    for (int i = 0; i < episodes.size(); i++) {
      EpisodesRecord ep = episodes.get(i);
      out.println(
              ep.getEpisode() + "\t" +
              ep.getTitle() + "\t" +
              ep.getAired());
    }
    out.close();
    myComponents.MyUsefulFunctions.message("Export completed", "Export of " + Series.getCurrentSerial().getFullTitle() + " is completed");
  }

  /**
   * The export file filter (*.eef)
   */
  public static class exportFilter extends FileFilter {

    /**
     * The file filter extensions
     * @param f
     * @return
     */
    public boolean accept(File f) {
      return f.isDirectory() || f.getName().toLowerCase().endsWith(".eef");
    }

    /**
     * The file filter description
     * @return
     */
    public String getDescription() {
      return "My Series episodes export file (*.eef)";
    }
  }
}