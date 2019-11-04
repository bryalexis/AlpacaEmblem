package model.tactician.handlers;

import model.tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class handles events when a Hero Die
 * @author Bryan Ortiz P
 * @since 2.5
 * @version 2.5
 */
public class HeroDieListener implements PropertyChangeListener {
  private Tactician tactician;

  /**
   * Creates a new HeroDie Listener
   * @param tactician owner of the hero
   */
  public HeroDieListener(Tactician tactician){
    this.tactician = tactician;
  }

  /**
   * Makes the player fire another event, that will be listened by the
   * Controller
   * @param evt hero's death
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    tactician.fireHeroDeath();
  }
}