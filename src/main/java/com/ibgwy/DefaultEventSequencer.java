package com.ibgwy;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * Not safe to use in multi-threaded environment
 * <p/>
 * User: Vishal Puri
 */
public class DefaultEventSequencer implements EventSequencer {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  private static final int SEED = 1;


  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private int id = SEED;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public DefaultEventSequencer() {
    this(SEED);
  }

  public DefaultEventSequencer(int seed) {
    if (seed <= 0) {
      throw new IllegalArgumentException("Seed must be greater than 0");
    }
    this.id = seed;
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  @Override
  public int nextId() {
    return id++;
  }

}
