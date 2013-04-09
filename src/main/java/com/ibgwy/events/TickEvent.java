package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 */
public abstract class TickEvent extends Event {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  /**
   * Specifies the type of price.
   */
  protected int type;


  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  protected TickEvent(int type) {
    super(EventTypes.Instrument.UPDATE);
    this.type = type;
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public int getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TickEvent tickEvent = (TickEvent) o;

    if (id != tickEvent.id) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return id;
  }

}
