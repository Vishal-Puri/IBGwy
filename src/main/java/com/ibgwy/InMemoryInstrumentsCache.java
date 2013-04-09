package com.ibgwy;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class InMemoryInstrumentsCache implements InstrumentsCache {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/


  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private static final Logger logger = LoggerFactory.getLogger(InMemoryInstrumentsCache.class);

  /**
   * Multiple threads reads and writes
   */
  private static ConcurrentMap<Integer, Instrument> instruments = Maps.newConcurrentMap();
    
    
  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public InMemoryInstrumentsCache(ConcurrentMap<Integer, Instrument> instruments) {
    Preconditions.checkNotNull(instruments);
    Preconditions.checkArgument(instruments.size() > 0);
    this.instruments = instruments;
  }
    
    
  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/


  /**
   * @param id Instrument id to lookup
   * @return Instrument if found, null otherwise
   */
  @Override
  public Instrument getInstrument(int id) {
    if (id < 0) {
      logger.warn("Invalid instrument lookup ");
    }

    Instrument instrument = instruments.get(id);
    if (instrument == null) {
      logger.warn("Instrument Id: " + id + " Not available");
    }
    return instrument;
  }

  /**
   * MarketDataRequest updates
   */
  @Override
  public void updateInstrument(int id, double price) {
    Instrument instrument = instruments.get(id);

    if (instrument == null) {
      logger.error("Invalid instrument update " + id);
      return;
    }
    instrument.setPrice(price);
  }

  /**
   * MarketDataRequest updates
   */
  @Override
  public void updateInstrument(int id, int qty) {
    Instrument instrument = instruments.get(id);

    if (instrument == null) {
      logger.error("Invalid instrument update " + id);
      return;
    }
    instrument.setQuantity(qty);
  }

}
