package com.ibgwy;

import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.ibgwy.events.Event;
import com.ibgwy.events.EventTypes;
import com.ibgwy.events.OrderPending;

import java.util.Set;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class EventBusWrapper<T extends Event> {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private EventBus eventBus;

  private EventSequencer sequencer;

  private Set<Integer> orderIds = Sets.newHashSet();

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public  EventBusWrapper(EventBus eventBus, EventSequencer sequencer) {
    this.eventBus = eventBus;
    this.sequencer = sequencer;
  }
    
    
  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public void register(Object event) {
    eventBus.register(event);
  }


  public void post(Event event) {
    switch (event.getEventType()) {
      case EventTypes.Orders.PENDING:
        OrderPending pending = (OrderPending) event;
        int orderId = pending.getOrderId();
        orderIds.add(orderId);
        int eventId = sequencer.nextId();
        pending.setId(eventId);
        break;
      case EventTypes.Instrument.UPDATE_REQUEST:
        InstrumentUpdateRequest instUpReq = (InstrumentUpdateRequest) event;
        instUpReq.setId(sequencer.nextId());
        break;
      default:
        break;
    }
    eventBus.post(event);
  }

  public void unregister(Object object) {
    eventBus.unregister(object);
  }
}
