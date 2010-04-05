/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myComponents.MyMessages;
import myComponents.MyUsefulFunctions;
import myseries.MySeries;
import tools.options.Options;

/**
 *
 * @author lordovol
 */
public class SaveDatabase {

  private MySeries m;
  private String name;
  public boolean backUp;

  public SaveDatabase(MySeries m) {
    try {
      this.m = m;
      showSavePane();
    } catch (FileNotFoundException ex) {
      MySeries.logger.log(Level.SEVERE, "Could not save database", ex);
      MyMessages.error("Database not saved!!!", "The database could not be saved (" + ex.getMessage() + ")");
    } catch (IOException ex) {
      MySeries.logger.log(Level.SEVERE, "Could not save database", ex);
      MyMessages.error("Database not saved!!!", "The database could not be saved (" + ex.getMessage() + ")");
    }
  }

  public SaveDatabase(String dbName) {
    try {
      String source = Options._USER_DIR_ + "/" + Options._DB_PATH_ + dbName;
      String dest = Options._USER_DIR_ + "/" + Options._DB_PATH_ + dbName + ".bak";
      if (MyUsefulFunctions.copyfile(source, dest)) {
        MySeries.logger.log(Level.INFO, "Database backed up!!!");
        MyMessages.message("Database backed up", "A back up of the older database was taken");
        backUp = true;
        return;
      } else {
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(SaveDatabase.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(SaveDatabase.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private boolean checkIfAlreadyExists() {
    File db = new File(Options._USER_DIR_ + "/" + Options._DB_PATH_ + name + ".db");
    if (db.isFile()) {
      return MyMessages.question("File Exists", "File already exists.\nOverwrite it?") == 1 ? true : false;
    } else {
      return false;
    }
  }

  private void commitSave() {
    try {
      String source = Options._USER_DIR_ + "/" + Options._DB_PATH_ + Options.toString(Options.DB_NAME);
      String dest = Options._USER_DIR_ + "/" + Options._DB_PATH_ + name + ".db";
      if (MyUsefulFunctions.copyfile(source, dest)) {
        MySeries.logger.log(Level.INFO, "Database saved");
        MyMessages.message("Database saved", "The database was saved with the name " + name + ".db");
        return;
      } else {
      }
    } catch (FileNotFoundException ex) {
      MySeries.logger.log(Level.SEVERE, "The database could not be saved", ex);
      MyMessages.error("Database not saved!!!", "The database could not be saved (" + ex.getMessage() + ")");
    } catch (IOException ex) {
      MySeries.logger.log(Level.SEVERE, "The database could not be saved", ex);
      MyMessages.error("Database not saved!!!", "The database could not be saved (" + ex.getMessage() + ")");
    }

  }

  private void showSavePane() throws FileNotFoundException, IOException {
    name = MyUsefulFunctions.getInput("Save Database", "Save Database As ");
    if (name == null) {
      MySeries.logger.log(Level.INFO, "Save Database aborted");
    } else {
      if (!checkIfAlreadyExists()) {
        if ((name).equals(Options.toString(Options.DB_NAME).replace(".db", ""))) {
          MyMessages.error("Error", "Cannot save the database on itself!!!");
          MySeries.logger.log(Level.WARNING, "Cannot save the database on itself!!!");
        } else {
          commitSave();
        }
      } else {
      }
    }
  }
}