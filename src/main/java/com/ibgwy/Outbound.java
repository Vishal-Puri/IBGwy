package com.ibgwy;

import com.ib.client.Contract;
import com.ibgwy.events.OrderPending;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public interface Outbound {

  void reqMktData(InstrumentUpdateRequest mktDataReq);

  void cancelHistoricalData(int tickerId);

  void reqHistoricalData(int tickerId, Contract contract,
                         String endDateTime, String durationStr,
                         String barSizeSetting, String whatToShow,
                         int useRTH, int formatDate);

  void cancelMktData(int tickerId);

  void placeOrder(OrderPending order);

  void cancelOrder(int id);

}
