package com.ibgwy.listeners;

import com.ibgwy.InstrumentUpdateRequest;
import com.ibgwy.events.InstrumentUpdate;

public interface MarketDataListener {

  void onMarketDataRequest(InstrumentUpdateRequest instrumentUpdateRequest);

  void onInstrumentUpdate(InstrumentUpdate instrumentUpdate);

  InstrumentUpdate getInstrumentUpdate(int id);


}
