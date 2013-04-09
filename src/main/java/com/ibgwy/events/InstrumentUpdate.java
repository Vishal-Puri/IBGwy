package com.ibgwy.events;

import com.ib.client.TickType;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class InstrumentUpdate extends Event {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private int instrumentId;

  private double askPrice;

  private double bidPrice;

  private int askSize;

  private int bidSize;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public InstrumentUpdate() {
    super(EventTypes.Instrument.UPDATE);
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public int getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(int instrumentId) {
    this.instrumentId = instrumentId;
  }

  public void setPrice(int tickType, double price) {

    switch (tickType) {
      case TickType.ASK:
        askPrice = price;
        break;
      case TickType.BID:
        bidPrice = price;
        break;
    }

  }

  public double getPrice(int type) {
    switch (type) {
      case TickType.ASK:
        return askPrice;
      case TickType.BID:
        return bidPrice;
      default:
        return 0d;
    }
  }

  public void setSize(int tickType, int size) {
    switch (tickType) {
      case TickType.ASK_SIZE:
        this.askSize = size;
        break;
      case TickType.BID_SIZE:
        this.bidSize = size;
        break;
    }
  }

  public int getSize(int type) {
    switch (type) {
      case TickType.ASK_SIZE:
        return askSize;
      case TickType.BID_SIZE:
        return bidSize;
      default:
        return 0;
    }
  }
}
