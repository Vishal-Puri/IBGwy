package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class OrderCancelled extends Event {


  public OrderCancelled(short eventType) {
    super(EventTypes.Orders.CANCELLED);
  }

}
