package model.units;

import model.units.carriers.Alpaca;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0),null);
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Override
  public IUnit getEquippedTestUnit() {
    return getTestUnit();
  }

}