package com.ibgwy;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public interface InstrumentsCache {

  Instrument getInstrument(int id);

  void updateInstrument(int id, double price);

  void updateInstrument(int id, int qty);
}
