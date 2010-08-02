/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myComponents.myToolbar;

import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ssoldatos
 */
public class ToolbarSeperator extends JLabel implements ToolbarButtonActions {

  private static final long serialVersionUID = 3249182490184L;
  private int actionName = NONE;
  private String tooltip;
  public Point origin = new Point();
  public Point startPoint = new Point();

  public ToolbarSeperator() {
    super();
  }

  ToolbarSeperator(int actionName, String tooltip, String image) {
    super();
    this.actionName = actionName;
    this.tooltip = tooltip;
    setOpaque(false);
    setBorder(BorderFactory.createEmptyBorder());
    setIcon(new ImageIcon(getClass().getResource("/images/sep.png")));
    setToolTipText(tooltip);
  }

  /**
   * @return the actionName
   */
  public int getActionName() {
    return actionName;
  }

  /**
   * @param actionName the actionName to set
   */
  public void setActionName(int actionName) {
    this.actionName = actionName;
  }

  /**
   * @return the tooltip
   */
  public String getTooltip() {
    return tooltip;
  }

  /**
   * @param tooltip the tooltip to set
   */
  public void setTooltip(String tooltip) {
    this.tooltip = tooltip;
  }


}
