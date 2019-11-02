package model.factory;

import model.map.Field;
import model.map.Location;

import java.util.Random;

/**
 * Units Factory
 * Creates instances of maps with its respective parameters.
 * @version 2.2
 * @since 2.2
 * @author Bryan Ortiz P
 */
public class FieldFactory {

  public Field createMap(long seed, int mapSize, boolean connectAll){
    Field field = new Field();
    field.setSeed(seed);
    Location locations[] = new Location[mapSize*mapSize];
    for(int i=0;i<mapSize;i++){
      for(int j=0; j<mapSize;j++) {
        locations[j+mapSize*i] = new Location(i,j);
      }
    }
    field.addCells(connectAll, locations);
    return field;
  }
}
