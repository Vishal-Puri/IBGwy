package com.ibgwy;

import com.google.common.base.Preconditions;
import com.ib.client.TickType;
import com.ibgwy.events.InstrumentUpdate;
import com.ibgwy.events.OrderPending;
import com.ibgwy.listeners.MarketDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

import static com.ibgwy.events.OrderPending.OrderSide;
import static com.ibgwy.events.OrderPending.OrderType;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class TradingTerminal {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  protected Logger logger = LoggerFactory.getLogger(TradingTerminal.class);

  private static final Calendar CALENDAR = Calendar.getInstance();

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private EventBusWrapper eventBus;

  private MarketDataListener marketDataListener;

  private PriceImprovementHandler priceImprovementHandler;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public TradingTerminal(EventBusWrapper eventBus,
                         MarketDataListener marketDataListener,
                         PriceImprovementHandler priceImprovementHandler) {
    Preconditions.checkNotNull(eventBus);
    this.eventBus = eventBus;
    this.marketDataListener = marketDataListener;
    this.priceImprovementHandler = priceImprovementHandler;
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/


  public void enterOrder(int instrumentId, int qty, OrderSide side, OrderType type) {

    InstrumentUpdateRequest mkdReqEvent = new InstrumentUpdateRequest(instrumentId, true);
    sendInstrumentUpdateRequest(mkdReqEvent);

    double price = -1d;
    if (side == OrderSide.BUY) {
      price = getPrice(TickType.ASK, mkdReqEvent.getId());
      price = priceImprovementHandler.priceImprovedBuy(price);
    } else if (side == OrderSide.SELL) {
      price = getPrice(TickType.BID, mkdReqEvent.getId());
      price = priceImprovementHandler.priceImprovedSell(price);
    }
    if (price == -1d) {
      if (logger.isDebugEnabled()) {
        logger.debug("Not creating order; MarketData request failed");
      }
      return;
    }
    placeOrder(instrumentId, qty, price, side, type);
  }

  public void placeOrder(int instrumentId, int qty, double price, OrderSide side, OrderType type) {
    OrderPending pending = new OrderPending();
    pending.setInstrumentId(instrumentId);
    pending.setPrice(price);
    pending.setQuantity(qty);
    pending.setDate(CALENDAR.getTimeInMillis());
    pending.setOrderSide(side);
    pending.setOrderType(type);
    eventBus.post(pending);
  }

  public void sendInstrumentUpdateRequest(InstrumentUpdateRequest request) {
    eventBus.post(request);
  }

  protected double getPrice(int tickerId, int tickType) {
    InstrumentUpdate instrumentUpdate = marketDataListener.getInstrumentUpdate(tickerId);
    if (instrumentUpdate != null) {
      instrumentUpdate.getPrice(tickType);
    }
    return -1d;
  }
}
