package com.ibgwy;

import com.ibgwy.events.InstrumentUpdate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class InMemoryTickCache implements TickCache {


  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private static final ConcurrentMap<Integer, InstrumentUpdate> cache = new ConcurrentHashMap<Integer,
      InstrumentUpdate>();

  private static final int WAIT_MILLIS = 1000;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/
    
    
  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/


  @Override
  public void addInstrumentUpdate(InstrumentUpdate tick) {
    cache.put(tick.getId(), tick);
  }

  @Override
  public InstrumentUpdate getInstrumentUpdate(int id) {
    if (cache.containsKey(id)) {
      return cache.get(id);
    }
    long wait = System.currentTimeMillis() + WAIT_MILLIS;
    while (System.currentTimeMillis() <= wait && !cache.containsKey(id)) {
      // wait
    }
    return cache.get(id);
  }


}
