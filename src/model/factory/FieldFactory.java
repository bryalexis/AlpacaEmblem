package model.factory;

import model.map.Field;
import model.map.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Units Factory
 * Creates instances of maps with its respective parameters.
 * @version 2.2
 * @since 2.2
 * @author Bryan Ortiz P
 */
public class FieldFactory {

  public Field createMap(long seed, int mapSize){
    Field field = new Field();
    for(int i=0;i<mapSize;i++){
      for(int j=0; j<mapSize;j++) {
        Location L = new Location(i, j);
        field.addCells(false, L);
      }
    }
    return field;
  }
}
