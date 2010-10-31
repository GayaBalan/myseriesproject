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
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import myComponents.MyMessages;
import myComponents.myTreeCellRenderers.FeedTreeCellRenderer;
import myseries.actions.FeedsActions;

/**
 *
 * @author lordovol
 */
public class FeedTree extends javax.swing.JPanel {

  public static final long serialVersionUID = 34573458937458934L;
  ArrayList<FeedLeaf> model = new ArrayList<FeedLeaf>();
  private DefaultTreeModel treemodel;
  private FeedLeaf selectedLeaf;

  /** Creates new form FeedTree */
  public FeedTree() {
    initComponents();
   
  }

  private void mouseReleased(java.awt.event.MouseEvent evt) {
    DefaultMutableTreeNode node;
    if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1) {
      Point p = evt.getPoint();
      TreePath selectedPath = tree.getClosestPathForLocation(p.x, p.y);
      if (tree.getPathBounds(selectedPath).contains(p)) {
        tree.setSelectionPath(selectedPath);
        node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node.isLeaf()) {
          FeedLeaf newValue = (FeedLeaf) node.getUserObject();
          FeedLeaf oldValue = selectedLeaf;
          selectedLeaf = newValue;
        }
      }
    } else if (evt.getButton() == MouseEvent.BUTTON3) {
      Point p = evt.getPoint();
      TreePath selectedPath = tree.getClosestPathForLocation(p.x, p.y);
      if (tree.getPathBounds(selectedPath).contains(p)) {
        tree.setSelectionPath(selectedPath);
        node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node.isLeaf()) {
          selectedLeaf = (FeedLeaf) node.getUserObject();
          popup.show(this, evt.getX(), evt.getY());
        }
      }
    }
  }

  public void setCellRenderer(DefaultTreeCellRenderer renderer) {
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

    popup = new javax.swing.JPopupMenu();
    edit = new javax.swing.JMenuItem();
    delete = new javax.swing.JMenuItem();
    FeedScrollPane = new javax.swing.JScrollPane();
    tree = new javax.swing.JTree();

    edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rss_edit.png"))); // NOI18N
    edit.setText("Edit Feed");
    edit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        editActionPerformed(evt);
      }
    });
    popup.add(edit);

    delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rss_delete.png"))); // NOI18N
    delete.setText("Delete Feed");
    delete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteActionPerformed(evt);
      }
    });
    popup.add(delete);

    tree.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 1));
    tree.setModel(treemodel);
    tree.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(java.awt.event.MouseEvent evt) {
        treeMouseReleased(evt);
      }
    });
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

  private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    if (MyMessages.question("Delete Feed", "Do you really want to delete this feed") == JOptionPane.YES_OPTION) {
      boolean deleteById = FeedsRecord.deleteById(selectedLeaf.id);
      if(deleteById){
        populate();
      }
    }
    
    popup.setVisible(false);
  }//GEN-LAST:event_deleteActionPerformed

  private void treeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseReleased
    mouseReleased(evt);
  }//GEN-LAST:event_treeMouseReleased

  private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
    FeedsActions.addFeedPanel(selectedLeaf.id);
  }//GEN-LAST:event_editActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane FeedScrollPane;
  private javax.swing.JMenuItem delete;
  private javax.swing.JMenuItem edit;
  private javax.swing.JPopupMenu popup;
  private javax.swing.JTree tree;
  // End of variables declaration//GEN-END:variables
}
