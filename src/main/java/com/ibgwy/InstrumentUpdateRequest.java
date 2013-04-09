package com.ibgwy;

import com.ibgwy.events.Event;
import com.ibgwy.events.EventTypes;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class InstrumentUpdateRequest extends Event {

  private final boolean snapshot;

  private int instrumentId;

  public InstrumentUpdateRequest(int instrumentId, boolean snapshot) {
    super(EventTypes.Instrument.UPDATE_REQUEST);
    this.instrumentId = instrumentId;
    this.snapshot = snapshot;
  }

  public int getInstrumentId() {
    return instrumentId;
  }

  public boolean isSnapshot() {
    return snapshot;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InstrumentUpdateRequest that = (InstrumentUpdateRequest) o;

    if (id != that.id) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return id;
  }
}
