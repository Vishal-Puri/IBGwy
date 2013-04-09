package com.ibgwy.listeners;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.eventbus.Subscribe;
import com.ibgwy.Outbound;
import com.ibgwy.events.OrderAccepted;
import com.ibgwy.events.OrderCancelled;
import com.ibgwy.events.OrderExecuted;
import com.ibgwy.events.OrderPending;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class OrderEventsListener implements ExecutionsHandler {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private Logger logger = LoggerFactory.getLogger(OrderEventsListener.class);

  private Map<Integer, OrderPending> pendings = Maps.newConcurrentMap();

  private Outbound outbound;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public OrderEventsListener(Outbound outbound) {
    Preconditions.checkNotNull(outbound);
    this.outbound = outbound;
  }
    
    
  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  @Subscribe
  public void onPending(OrderPending pending) {
    pendings.put(pending.getOrderId(), pending);
    outbound.placeOrder(pending);
  }


  /**
   * @param executed
   */
  @Subscribe
  public void onExecuted(OrderExecuted executed) {
    // TODO: Add to Running Journal
    if (executed.getRemaining() == 0) {
      pendings.remove(executed.getId());
    }
  }

  @Subscribe
  public void onCancelled(OrderCancelled cancelled) {

  }

  @Subscribe
  public void onAccepted(OrderAccepted accepted) {

  }


}
