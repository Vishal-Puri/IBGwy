package com.ibgwy.listeners;

import com.google.common.base.Preconditions;
import com.ibgwy.InstrumentUpdateRequest;
import com.ibgwy.Outbound;
import com.ibgwy.TickCache;
import com.ibgwy.events.InstrumentUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO: Add JavaDoc!
 */
public class DefaultMarketDataListener implements MarketDataListener {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private Logger logger = LoggerFactory.getLogger(DefaultMarketDataListener.class);

  private Set<Integer> tickRequestIds = new HashSet<Integer>();

  private TickCache tickCache;

  private Outbound outbound;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public DefaultMarketDataListener(Outbound outbound, TickCache tickCache) {
    Preconditions.checkNotNull(outbound);
    Preconditions.checkNotNull(tickCache);
    this.outbound = outbound;
    this.tickCache = tickCache;
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  @Override
  public void onMarketDataRequest(InstrumentUpdateRequest instrumentUpdateRequest) {
    if (logger.isDebugEnabled()) {
      logger.debug("Sending MarketDataRequest " + instrumentUpdateRequest);
    }
    tickRequestIds.add(instrumentUpdateRequest.getId());
    outbound.reqMktData(instrumentUpdateRequest);
  }

  @Override
  public void onInstrumentUpdate(InstrumentUpdate instrumentUpdate) {
    int tickId = instrumentUpdate.getId();
    if (tickRequestIds.contains(tickId)) {
      tickCache.addInstrumentUpdate(instrumentUpdate);
    }
  }

  @Override
  public InstrumentUpdate getInstrumentUpdate(int id) {
    return tickCache.getInstrumentUpdate(id);
  }
}
