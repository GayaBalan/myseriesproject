/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myseries.actions;

import database.DBConnection;
import database.DBHelper;
import database.Database;
import database.EpisodesRecord;
import database.FilterRecord;
import database.SaveDatabase;
import database.SeriesRecord;
import help.About;
import help.CheckUpdate;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumnModel;
import myComponents.MyMessages;
import myComponents.MyUsefulFunctions;
import myseries.series.AdminSeries;
import myseries.series.Series;
import myseries.MySeries;
import myseries.StartPanel;
import myseries.episodes.AdminEpisodes;
import myseries.episodes.Episodes;
import myseries.episodes.NextEpisodes;
import myseries.episodes.Video;
import myseries.filters.Filters;
import tools.DesktopSupport;
import tools.download.subtitles.Subtitle;
import tools.download.subtitles.sonline.GetSOnlineCode;
import tools.download.subtitles.sonline.SOnlineForm;
import tools.download.subtitles.tvsubtitles.GetTvSubtitlesCode;
import tools.download.subtitles.tvsubtitles.TvSubtitlesForm;
import tools.download.torrents.AbstractTorrent;
import tools.download.torrents.TorrentConstants;
import tools.download.torrents.eztv.EzTvForm;
import tools.download.torrents.isohunt.IsohuntForm;
import tools.importExport.ExportEpisodes;
import tools.importExport.ImportEpisodes;
import tools.internetUpdate.InternetUpdate;
import tools.internetUpdate.tvrage.TrGetId;
import tools.options.Options;
import tools.options.OptionsPanel;
import tools.renaming.RenameEpisodes;

/**
 *
 * @author lordovol
 */
public class Actions {

  public static void editSeries(MySeries m) {
    try {
      MySeries.glassPane.activate(null);
      AdminSeries a = new AdminSeries(m, Series.getCurrentSerial());
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }

  }

  public static void addSeries(MySeries m) {
    Series.setCurrentSerial(null);
    MySeries.glassPane.activate(null);
    //if (!addSeriesPanel) {
    try {
      AdminSeries a = new AdminSeries(m, null);
      Series.setCurrentSerial(null);
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }

  }

  public static void deleteSeries(MySeries m) {
    String title = Series.getCurrentSerial().getTitle();
    int season = Series.getCurrentSerial().getSeason();
    int series_ID = Series.getCurrentSerial().getSeries_ID();
    String screenshot = Series.getCurrentSerial().getScreenshot();
    int answ = MyMessages.question("Delete Serial?", "Really delete the series " + title + " season " + season + "?");
    if (answ == 0) {
      try {
        String sql = "DELETE FROM series WHERE series_ID = " + series_ID;
        DBConnection.stmt.execute(sql);
        sql = "DELETE FROM episodes WHERE series_ID = " + series_ID;
        DBConnection.stmt.execute(sql);
        File screenshotFile = new File("./images/" + screenshot);
        if (screenshotFile.isFile()) {
          screenshotFile.delete();
          Image image = new ImageIcon(MySeries.class.getResource("/images/logo.png")).getImage();
          m.imagePanel.setImage(image);
        }
        Series.getSeries();
        Series.setCurrentSerial(null);
        Episodes.updateEpisodesTable();
        NextEpisodes.createNextEpisodes();
        NextEpisodes.show();
      } catch (SQLException ex) {
        MySeries.logger.log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void AddEpisode(MySeries m) {
    try {
      MySeries.glassPane.activate(null);
      AdminEpisodes e = new AdminEpisodes(m, Series.getCurrentSerial(), null);
    } catch (IOException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void filterSubtitles(JComboBox comboBox_subtitles) {
    try {
      //Filters.setSubtitles(comboBox_subtitles.getSelectedIndex());
      Filters.getFilteredSeries();
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void filterSeen(JComboBox comboBox_seen) {
    try {
      //Filters.setSeen(comboBox_seen.getSelectedIndex());
      Filters.getFilteredSeries();
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void createDatabase(MySeries m, boolean createNewDb) {
    MySeries.glassPane.activate(null);
    new StartPanel(m, createNewDb);
  }

  public static void loadDatabase(MySeries m) {
    try {
      String[] filter = {".db"};
      String load = MyUsefulFunctions.getSelectedFile(Database.PATH, filter, "Load Database", "Select the database to load");
      if (!load.equals("null")) {
        if (DBConnection.checkDatabase(load)) {
          Options.setOption(Options.DB_NAME, load);
          Options.save();
          m.dispose();
          new MySeries();
        } else {
          MySeries.logger.log(Level.WARNING, "Selected database is invlid.Not loading...");
          MyMessages.error("Invalid Database", "The database you selected is invalid");
          loadDatabase(m);
        }
      } else {
      }
    } catch (ClassNotFoundException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void filterDownloaded(JComboBox combobox_downloaded) {
    try {
      //Filters.setDownloaded(combobox_downloaded.getSelectedIndex());
      Filters.getFilteredSeries();
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void showOptions(MySeries m) {
    MySeries.glassPane.activate(null);
    OptionsPanel a = new OptionsPanel(m);
  }

  public static void exitApplication(MySeries m) {
    int divLocation = MySeries.splitPane_main.getDividerLocation();
    Options.setOption(Options.DIVIDER_LOCATION, divLocation);
    Options.setOption(Options.WINDOW_STATE, m.getExtendedState());
    Options.setOption(Options.WIDTH, m.getWidth());
    Options.setOption(Options.HEIGHT, m.getHeight());
    ArrayList<Integer> w = getTablesWidths(m);
    Options.setOption(Options.TABLE_WIDTHS, w);
    MySeries.logger.log(Level.INFO, "Saving options");
    Options.save();
    MySeries.logger.log(Level.INFO, "Application exiting...");
    System.exit(0);
  }

  private static ArrayList<Integer> getTablesWidths(MySeries m) {
    ArrayList<Integer> widths = new ArrayList<Integer>();
    TableColumnModel ts = MySeries.tableSeries.getColumnModel();
    for (int i = 0; i < ts.getColumnCount(); i++) {
      widths.add(ts.getColumn(i).getWidth());
    }
    ts = MySeries.tableEpisodes.getColumnModel();
    for (int i = 0; i < ts.getColumnCount(); i++) {
      widths.add(ts.getColumn(i).getWidth());
    }
    ts = MySeries.tableFilters.getColumnModel();
    for (int i = 0; i < ts.getColumnCount(); i++) {
      widths.add(ts.getColumn(i).getWidth());
    }

    return widths;
  }

  public static void about(MySeries m) {
    MySeries.glassPane.activate(null);
    About a = new About(m);
  }

  public static void saveFilter(MySeries m) {
    String title = "";
    title = String.valueOf(MySeries.combobox_filters.getSelectedItem());
    FilterRecord f;
    if (title.trim().equals("") || title.equals("null")) {
      MyMessages.error("Empty title", "Please specify a save name");
    } else {
      try {
        f = DBHelper.getFilterByTitle(title);
        if (f == null) {
          f = new FilterRecord();
        }
        f.setDownloaded(MySeries.combobox_downloaded.getSelectedIndex());
        f.setSeen(MySeries.comboBox_seen.getSelectedIndex());
        f.setSubtitles(MySeries.comboBox_filterSubtitles.getSelectedIndex());
        f.setTitle(title);
        f.save();
        MyMessages.message("Filter saved", "Filter was saved");
        m.comboBoxModel_filters = new DefaultComboBoxModel(DBHelper.getFiltersTitlesList());
        MySeries.combobox_filters.setModel(m.comboBoxModel_filters);
      } catch (SQLException ex) {
        MySeries.logger.log(Level.WARNING, "Error while saving filter", ex);
        MyMessages.error("SQL Error", "There was an error when saving the filter");
      }
    }
  }

  public static void deleteFilter(MySeries m) {
    String title = "";
    title = String.valueOf(MySeries.combobox_filters.getSelectedItem());
    FilterRecord f;
    int answ = MyMessages.question("Delete Filter?", "Are you sure that you want to delete the filter?");
    if (answ == 0) {
      try {
        f = DBHelper.getFilterByTitle(title);
        if (f != null) {
          f.delete();
          MyMessages.message("Filter deleted", "Filter was deleted");
        } else {
          MyMessages.error("Error", "Filter could not be deleted");
        }
        m.comboBoxModel_filters = new DefaultComboBoxModel(DBHelper.getFiltersTitlesList());
        MySeries.combobox_filters.setModel(m.comboBoxModel_filters);
      } catch (SQLException ex) {
        MySeries.logger.log(Level.WARNING, "Error while deleting filter", ex);
        MyMessages.error("SQL Error", "There was an error when deleting the filter");
      }
    }
  }

  public static void applyFilter(MySeries m) {
    try {
      String title = "";
      title = String.valueOf(MySeries.combobox_filters.getSelectedItem());
      FilterRecord f = DBHelper.getFilterByTitle(title);
      if (f != null) {
        MySeries.combobox_downloaded.setSelectedIndex(f.getDownloaded());
        MySeries.comboBox_seen.setSelectedIndex(f.getSeen());
        MySeries.comboBox_filterSubtitles.setSelectedIndex(f.getSubtitles());
      }
    } catch (SQLException ex) {
      MySeries.logger.log(Level.WARNING, "Error while applying the  filter", ex);
      MyMessages.error("SQL Error", "There was an error when applying the filter");
    }
  }

  public static void AddEpisodeInEpisodes(MySeries m) {
    try {
      MySeries.glassPane.activate(null);
      AdminEpisodes e = new AdminEpisodes(m, Series.getCurrentSerial(), null);
    } catch (IOException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void goToSubtitlePage(MySeries m, String site) {
    try {
      if (!DesktopSupport.isBrowseSupport()) {
        MySeries.logger.log(Level.WARNING, "Browse is not supported in the current OS");
        MyMessages.error("Browse Error!!!", "Browse is not supported");
        return;
      }
      java.net.URI uri = null;
      if (site.equals(Subtitle.TV_SUBTITLES_NAME)) {
        uri = new java.net.URI("http://www.tvsubtitles.net/tvshow-" + Series.getCurrentSerial().getTvSubtitlesCode() + ".html");
      } else if (site.equals(Subtitle.SUBTITLE_ONLINE_NAME)) {
        uri = new java.net.URI("http://www.subtitleonline.com/" + Series.getCurrentSerial().getSOnlineCode() + "-season-" + Series.getCurrentSerial().getSeason() + "-subtitles.html");
      }
      DesktopSupport.getDesktop().browse(uri);
    } catch (IOException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    } catch (URISyntaxException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void exportEpisodes() {
    new ExportEpisodes();
  }

  public static void importEpisodes(MySeries m) {
    try {
      MySeries.glassPane.activate(null);
      new ImportEpisodes(m);
    } catch (SQLException ex) {
      MySeries.logger.log(Level.WARNING, "Error while importing the episodes", ex);
      MyMessages.error("SQL Error", "There was an error when importing the episodes");
    }
  }

  public static void saveDatase() {
    new SaveDatabase();
  }

  public static void internetUpdate(MySeries m, String site) {
    MySeries.glassPane.activate(null);
    new InternetUpdate(m, site);
  }

  public static void internetUpdateSeries(MySeries m, String site) {
    MySeries.glassPane.activate(null);
    SeriesRecord cSeries = Series.getCurrentSerial();
    if (site.equals(InternetUpdate.TV_RAGE_NAME) && cSeries.getTvrage_ID() == 0) {
      try {
        TrGetId g = new TrGetId(m, cSeries.getSeries_ID(), cSeries.getTitle());
        cSeries.setTvrage_ID(g.tvRageID);
        cSeries.save();
      } catch (SQLException ex) {
        MySeries.logger.log(Level.SEVERE, null, ex);
      }
    } else {
      InternetUpdate iu = new InternetUpdate(m, Series.getCurrentSerial(), site);
    }
    MySeries.glassPane.deactivate();
  }

  public static void checkUpdates() {
    MySeries.glassPane.activate(null);
    new CheckUpdate(false);
  }

  public static void clearLogFiles() {
    File dir = new File(Options._USER_DIR_);
    File[] files = dir.listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File dir, String name) {
        if (name.matches("MySeriesLogs_([1-9]\\.html$|[0-9]\\.html\\.\\d+)")) {
          return true;
        }
        return false;
      }
    });
    if (files.length == 0) {
      MyMessages.message("Delete log files", "There are no old log files to delete");
      return;
    }
    int ans = MyMessages.question("Delete log files",
            "Realy delete the following " + files.length + " log files:\n"
            + MyUsefulFunctions.listAray(files, true));
    if (ans == JOptionPane.YES_OPTION) {
      for (int i = 0; i < files.length; i++) {
        File file = files[i];
        file.delete();
      }

    }
  }

  public static void viewLog(MySeries m) {
    Desktop d = Desktop.getDesktop();
    if (!Desktop.isDesktopSupported()) {
      MyMessages.error("Sorry!!!", "Your OS does not support this function");
    } else {
      if (!d.isSupported(Desktop.Action.OPEN)) {
        MyMessages.error("Sorry!!!", "Your OS does not support this function");
      } else {
        try {
          d.open(new File(Options._USER_DIR_ + "/MySeriesLogs_0.html"));
        } catch (IOException ex) {
          MySeries.logger.log(Level.SEVERE, "Could not read the log file", ex);
        }
      }
    }
  }

  public static void goToLocalDir() {
    try {
      File f = new File(Series.getCurrentSerial().getLocalDir());
      if (f.isDirectory()) {
        DesktopSupport.getDesktop().open(f);
      } else {
        MySeries.logger.log(Level.WARNING, f.getCanonicalPath() + " is not a directory");
        MyMessages.error("Directory error", f.getCanonicalPath() + " is not a directory");
        return;
      }
    } catch (Exception ex) {
      MySeries.logger.log(Level.WARNING, "Browse is not supported in the current OS");
      MyMessages.error("Browse Error!!!", "Browse is not supported");
      return;
    }
  }

  public static void viewEpisode() {
    File localDir = new File(Series.getCurrentSerial().getLocalDir().trim());
    int season = Series.getCurrentSerial().getSeason();
    int episode = Episodes.getCurrentEpisode().getEpisode();
    String regex = MyUsefulFunctions.createRegex(season, episode);
    Video.getVideos(localDir, regex);
  }

  public static void changeTab(MySeries m, ChangeEvent evt) {
    Vector<SeriesRecord> series = null;
    JTabbedPane tabs = (JTabbedPane) evt.getSource();
    int index = tabs.getSelectedIndex();
    try {
      if (index == MySeries.TAB_SERIES) {
        if (!MySeries.tabsPanel.getTitleAt(MySeries.TAB_SERIES).equals("")) {
          String title = MySeries.tabsPanel.getTitleAt(MySeries.TAB_SERIES).substring(
                  0, MySeries.tabsPanel.getTitleAt(MySeries.TAB_SERIES).length() - 3).trim();
          series = DBHelper.getSeriesBySql("SELECT * FROM series WHERE title = '" + title + "'");
        } else {
          series = DBHelper.getSeriesBySql("SELECT * FROM series LIMIT 1");
        }
        if (series.size() > 0) {
          Series.setCurrentSerial(series.get(0));
          Episodes.updateEpisodesTable();
        } else {
        }
      } else if (index == MySeries.TAB_FILTERS) {
        Filters.getFilteredSeries();
      } else if (index == MySeries.TAB_STATISTICS) {
        m.table_stat_series.setTableModel();
        m.table_stat_episodes.setTableModel();
      }

    } catch (SQLException ex) {
      MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void deleteEpisode() {
    String title = Episodes.getCurrentEpisode().getTitle();
    int episode_ID = Episodes.getCurrentEpisode().getEpisode_ID();
    int answ = MyMessages.question("Delete Episode?", "Really delete the episode " + title + "?");
    if (answ == JOptionPane.YES_OPTION) {
      try {
        String sql = "DELETE FROM episodes WHERE episode_ID = " + episode_ID;
        DBConnection.stmt.execute(sql);
        Episodes.updateEpisodesTable();
      } catch (SQLException ex) {
        MySeries.logger.log(Level.SEVERE, null, ex);
      }
    } else {
    }
  }

  public static void deleteEpisodes(ArrayList<EpisodesRecord> episodes) {
    int answ = MyMessages.question("Delete Episode?", "Really delete the selected episodes ?");
    if (answ == JOptionPane.YES_OPTION) {
      for (Iterator<EpisodesRecord> it = episodes.iterator(); it.hasNext();) {
        EpisodesRecord e = it.next();
        String sql = "DELETE FROM episodes WHERE episode_ID = " + e.getEpisode_ID();
        try {
          DBConnection.stmt.execute(sql);
        } catch (SQLException ex) {
          MySeries.logger.log(Level.SEVERE, null, ex);
        }
      }
      try {
        Episodes.updateEpisodesTable();
      } catch (SQLException ex) {
        MySeries.logger.log(Level.SEVERE, null, ex);
      }
    }
  }

  public static void renameEpisode() {
    ArrayList<File> oldNames = new ArrayList<File>();
    ArrayList<EpisodesRecord> newNames = new ArrayList<EpisodesRecord>();
    SeriesRecord series = Series.getCurrentSerial();
    int season = series.getSeason();
    EpisodesRecord episodeRecord = Episodes.getCurrentEpisode();
    File dir = new File(series.getLocalDir());
    File[] files = dir.listFiles();
    String path;
    int episode = episodeRecord.getEpisode();
    String regex = MyUsefulFunctions.createRegex(season, episode);
    Pattern pattern = Pattern.compile(regex);
    for (int i = 0; i < files.length; i++) {
      File file = files[i];
      if (file != null && file.isFile()) {
        path = file.getParent();
        String name = file.getName();
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
          String[] tokens = name.split("\\.", -1);
          String ext = tokens[tokens.length - 1];
          if (ext.equals("srt") || ext.equals("sub")) {
            if (tokens[tokens.length - 2].equals("gr") || tokens[tokens.length - 2].equals("en")) {
              ext = tokens[tokens.length - 2] + "." + tokens[tokens.length - 1];
            }
          }

          String newFilename = series.getTitle()
                  + Options.toString(Options.SEASON_SEPARATOR, false) + MyUsefulFunctions.padLeft(series.getSeason(), 2, "0")
                  + Options.toString(Options.EPISODE_SEPARATOR, false) + MyUsefulFunctions.padLeft(episodeRecord.getEpisode(), 2, "0")
                  + Options.toString(Options.TITLE_SEPARATOR, false) + episodeRecord.getTitle();

          String newName = path + "/" + newFilename + "." + ext;
          File newFile = new File(newName);
          oldNames.add(files[i]);
          newNames.add(episodeRecord);
          files[i] = null;
        }
      }
    }
    if (oldNames.size() > 0) {
      RenameEpisodes r = new RenameEpisodes(oldNames, newNames, series);
    } else {
      MyMessages.message("No files to rename", "There are no available files to rename");
    }
  }

  public static void renameEpisodes() {
    try {
      ArrayList<File> oldNames = new ArrayList<File>();
      ArrayList<EpisodesRecord> newNames = new ArrayList<EpisodesRecord>();
      SeriesRecord series = Series.getCurrentSerial();
      int season = series.getSeason();
      File dir = new File(series.getLocalDir());
      File[] files = dir.listFiles();
      String path;

      ArrayList<EpisodesRecord> episodes = Episodes.getCurrentSeriesEpisodes();
      for (Iterator<EpisodesRecord> it = episodes.iterator(); it.hasNext();) {
        EpisodesRecord episodesRecord = it.next();
        int episode = episodesRecord.getEpisode();
        String regex = MyUsefulFunctions.createRegex(season, episode);
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < files.length; i++) {
          File file = files[i];
          if (file != null && file.isFile()) {
            path = file.getParent();
            String name = file.getName();
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
              String[] tokens = name.split("\\.", -1);
              String ext = tokens[tokens.length - 1];
              if (ext.equals("srt") || ext.equals("sub")) {
                if (tokens[tokens.length - 2].equals("gr") || tokens[tokens.length - 2].equals("en")) {
                  ext = tokens[tokens.length - 2] + "." + tokens[tokens.length - 1];
                }
              }

              String newFilename = series.getTitle()
                      + Options.toString(Options.SEASON_SEPARATOR, false) + MyUsefulFunctions.padLeft(series.getSeason(), 2, "0")
                      + Options.toString(Options.EPISODE_SEPARATOR, false) + MyUsefulFunctions.padLeft(episodesRecord.getEpisode(), 2, "0")
                      + Options.toString(Options.TITLE_SEPARATOR, false) + episodesRecord.getTitle();

              String newName = path + "/" + newFilename + "." + ext;
              File newFile = new File(newName);
              oldNames.add(files[i]);
              newNames.add(episodesRecord);
              files[i] = null;
            }
          }
        }
      }
      if (oldNames.size() > 0) {
        RenameEpisodes r = new RenameEpisodes(oldNames, newNames, series);
      } else {
        MyMessages.message("No files to rename", "There are no available files to rename");
      }
    } catch (SQLException ex) {
      myseries.MySeries.logger.log(Level.SEVERE, null, ex);
    }
  }

  public static void downloadSubtitles(String site) {
    if (site.equals(Subtitle.TV_SUBTITLES_NAME)) {
      SeriesRecord series = Series.getCurrentSerial();
      String link = series.getTvSubtitlesCode().trim();
      boolean updateLink = false;
      if (link.startsWith(Subtitle.TV_SUBTITLES_URL)) {
        link = link.replaceAll("(" + Subtitle.TV_SUBTITLES_URL + "/tvshow-)|(.html)", "");
        updateLink = true;
      }
      if (MyUsefulFunctions.isNumeric(link)) {
        link = link + "-" + series.getSeason();
        updateLink = true;
      }
      if (!MyUsefulFunctions.isNumeric(link.replace("-", ""))) {
        link = "";
        updateLink = true;
      }
      if (link.equals("")) {
        GetTvSubtitlesCode s = new GetTvSubtitlesCode(Series.getCurrentSerial());
        link = s.tSubCode;
        updateLink = true;
      }
      if (updateLink) {
        series.setTvSubtitlesCode(link);
        try {
          series.save();
        } catch (SQLException ex) {
          MyMessages.error("SQL Error", "Could not update series link");
          myseries.MySeries.logger.log(Level.WARNING, "Could not update series link", ex);
        }
      }
      if (link != null && !link.equals("")) {
        TvSubtitlesForm d = new TvSubtitlesForm(
                Subtitle.TV_SUBTITLES_URL + "tvshow-" + link + ".html",
                Series.getCurrentSerial().getSeason(),
                Episodes.getCurrentEpisode().getEpisode(),
                Series.getCurrentSerial().getLocalDir(),
                Episodes.getCurrentEpisode().getTitle());
      }
    } else if (site.equals(Subtitle.SUBTITLE_ONLINE_NAME)) {
      String sOnlineCode = Series.getCurrentSerial().getSOnlineCode().trim();
      if (sOnlineCode.equals("")) {
        GetSOnlineCode s = new GetSOnlineCode(Series.getCurrentSerial());
        sOnlineCode = s.sOnlineCode;
        if (!sOnlineCode.equals("")) {
          SeriesRecord ser = Series.getCurrentSerial();
          ser.setSOnlineCode(sOnlineCode);
          try {
            ser.save();
          } catch (SQLException ex) {
            myseries.MySeries.logger.log(Level.WARNING, "Could not save sOnlineCode", ex);
          }
          getSOnlineSubtitle(sOnlineCode);
        }
      } else {
        getSOnlineSubtitle(sOnlineCode);
      }
    }
  }

  private static void getSOnlineSubtitle(String sOnlineCode) {
    SOnlineForm d = new SOnlineForm(
            sOnlineCode,
            Series.getCurrentSerial().getSeason(),
            Episodes.getCurrentEpisode().getEpisode(),
            Series.getCurrentSerial().getLocalDir(),
            Episodes.getCurrentEpisode().getTitle());
  }

  public static void downloadEpisodesTorrent() {
    SeriesRecord series = Series.getCurrentSerial();
    EpisodesRecord episode = Episodes.getCurrentEpisode();
    new EzTvForm(series, episode);
  }

  public static void downloadTorrent(String site) {
    if (site.equals(TorrentConstants.EZTV_NAME)) {
      new EzTvForm();
    } else if (site.equals(TorrentConstants.ISOHUNT_NAME)) {
      new IsohuntForm();
    }

  }

  public void start(final Runnable r) {
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        Thread t = new Thread(r);
        t.start();
      }
    });
  }
}
