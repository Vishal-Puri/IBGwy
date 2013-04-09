package com.ibgwy;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public interface InstrumentsCache {

  Instrument getInstrument(int id);

  void addInstrument(Instrument instrument);

}
