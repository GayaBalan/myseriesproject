/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FeedTree.java
 *
 * Created on 31 Οκτ 2010, 12:07:20 μμ
 */
package tools.feeds;

import database.Database;
import database.FeedsRecord;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import myComponents.myTreeCellRenderers.FeedTreeCellRenderer;

/**
 *
 * @author lordovol
 */
public class FeedTree extends javax.swing.JPanel {

  public static final long serialVersionUID = 34573458937458934L;
  ArrayList<FeedLeaf> model = new ArrayList<FeedLeaf>();
  private DefaultTreeModel treemodel;

  /** Creates new form FeedTree */
  public FeedTree() {
    initComponents();
  }

  public void setCellRenderer(DefaultTreeCellRenderer renderer){
    tree.setCellRenderer(renderer);
  }

  
  public void populate() {
    try {
      model.clear();
      ResultSet rs = FeedsRecord.getAll();
      while (rs.next()) {
        FeedLeaf l = new FeedLeaf();
        l.id = rs.getInt("feed_ID");
        l.title = rs.getString("title");
        l.url = rs.getString("url");
        model.add(l);
      }
      DefaultMutableTreeNode root = createTree();
      treemodel = new DefaultTreeModel(root);
      tree.setModel(treemodel);
    } catch (SQLException ex) {
      myseries.MySeries.logger.log(Level.SEVERE, null, ex);
    }

  }

  protected DefaultMutableTreeNode createTree() {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Rss Feeds");
    for (Iterator<FeedLeaf> it = model.iterator(); it.hasNext();) {
      FeedLeaf feedLeaf = it.next();
      DefaultMutableTreeNode listNode = new DefaultMutableTreeNode(feedLeaf);
      root.add(listNode);
    }
    return root;
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    FeedScrollPane = new javax.swing.JScrollPane();
    tree = new javax.swing.JTree();

    tree.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 1));
    tree.setModel(treemodel);
    FeedScrollPane.setViewportView(tree);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(FeedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(FeedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane FeedScrollPane;
  private javax.swing.JTree tree;
  // End of variables declaration//GEN-END:variables
}
