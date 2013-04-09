package com.ibgwy.listeners;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import com.ib.client.*;
import com.ibgwy.*;
import com.ibgwy.events.InstrumentUpdate;
import com.ibgwy.events.OrderPending;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

import static com.ibgwy.IBApiHelper.IBOrderStatusCode;
import static org.mockito.Mockito.*;

/**
 * User: Vishal Puri
 */
public class TradingTerminalTests {

  private TradingTerminal tradingTerminal;
  private InBound inBound;
  private Outbound outbound;
  private EClientSocket socket;
  private IBApiHelper ibHelper;
  private MarketDataListener marketDataListener;

  public TradingTerminalTests() {
    AnyWrapper ibApi = mock(AnyWrapper.class);
    socket = mock(EClientSocket.class);

    InstrumentsCache instrumentsCache = prepareInstrumentsCache();
    ibHelper = new IBApiHelper(instrumentsCache);

    outbound = new OutboundImpl(ibApi, " ", 0, 1, ibHelper, instrumentsCache);

    EventBusWrapper bus = new EventBusWrapper(new EventBus(), new DefaultEventSequencer());

    OrderEventsListener orderEventsListener = new OrderEventsListener(outbound);
    bus.register(orderEventsListener);

    EventsLogger eventsLogger = new EventsLogger(instrumentsCache);
    bus.register(eventsLogger);

    marketDataListener = mock(DefaultMarketDataListener.class);

    PriceImprovementHandler priceImprovementHandler = new DefaultPriceImprovementHandler();

    tradingTerminal = new TradingTerminal(bus, marketDataListener, priceImprovementHandler);
    inBound = new InBound(bus, ibHelper);

  }

  private InstrumentsCache prepareInstrumentsCache() {
    ConcurrentMap<Integer, Instrument> maps = Maps.newConcurrentMap();
    Instrument instrument = new Instrument(4440, Instrument.InstrumentType.STK, "AAPL", "NASDQ", "USD");
    maps.put(4440, instrument);
    return new InMemoryInstrumentsCache(maps);
  }

  @Test
  public void testExecutions() {
    int ordId = 1;
    int instrumentId = 4440;
    double price = 10.0;
    int qty = 100;

    int expectedOrdEvtId = 1;
    int expectedInsUpEvtId = 2;

    OrderPending.OrderSide side = OrderPending.OrderSide.BUY;
    OrderPending.OrderType type = OrderPending.OrderType.MKT;

    InstrumentUpdate instrumentUpdate = new InstrumentUpdate();
    instrumentUpdate.setPrice(TickType.ASK, price);
    instrumentUpdate.setPrice(TickType.BID, price);

    instrumentUpdate.setSize(TickType.BID_SIZE, qty);
    instrumentUpdate.setSize(TickType.ASK_SIZE, qty);

    // expectations
    when(marketDataListener.getInstrumentUpdate(expectedInsUpEvtId)).thenReturn(instrumentUpdate);
    socket.placeOrder(ordId, new Contract(), new Order());

    tradingTerminal.enterOrder(instrumentId, qty, side, type);

    verify(marketDataListener).getInstrumentUpdate(expectedInsUpEvtId);

    inBound.orderStatus(ordId, IBOrderStatusCode.Filled.name(), 10, 2, 100.0, 1, -1, 10, 1, " ");


  }


}
