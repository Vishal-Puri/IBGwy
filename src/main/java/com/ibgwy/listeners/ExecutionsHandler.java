package com.ibgwy.listeners;

import com.ibgwy.events.OrderExecuted;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public interface ExecutionsHandler {

  void onExecuted(OrderExecuted executed);

}
