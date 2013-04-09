package com.ibgwy;

import com.ibgwy.events.InstrumentUpdate;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public interface TickCache {

  void addInstrumentUpdate(InstrumentUpdate tick);

  InstrumentUpdate getInstrumentUpdate(int id);

}
