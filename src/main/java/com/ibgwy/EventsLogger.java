package com.ibgwy;

import com.google.common.eventbus.Subscribe;
import com.ibgwy.events.OrderExecuted;
import com.ibgwy.events.OrderPending;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class EventsLogger {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  private static final Logger logger = LoggerFactory.getLogger(EventsLogger.class);

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private InstrumentsCache instrumentsCache;





  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public EventsLogger(InstrumentsCache instrumentsCache) {
    this.instrumentsCache = instrumentsCache;
  }


  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  @Subscribe
  public void log(OrderPending pending) {
    final StringBuilder sb = new StringBuilder();
    sb.append(" -> OrderPending");
    sb.append("{orderId=").append(pending.getOrderId());
    sb.append(", parentId=").append(pending.getParentId());
    sb.append(", instrument=").append(instrumentsCache.getInstrument(pending.getInstrumentId()).getSymbol());
    sb.append(", price=").append(pending.getPrice());
    sb.append(", qty=").append(pending.getQuantity());
    sb.append(", client=").append(pending.getClientId());
    sb.append('}');
    logger.info(sb.toString());
  }

  public void log(OrderExecuted executed) {
    logger.info(" <- " + executed.toString());
  }


}
