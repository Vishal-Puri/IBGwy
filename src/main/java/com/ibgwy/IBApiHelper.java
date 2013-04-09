package com.ibgwy;

import com.ib.client.Contract;
import com.ib.client.Order;
import com.ibgwy.events.OrderPending;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Add JavaDoc!
 */
public class IBApiHelper {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  private static final Logger logger = LoggerFactory.getLogger(IBApiHelper.class);

  public static enum OrderStatus {

    PendingSubmit,

    PendingCancel,

    PreSubmitted,

    Submitted,

    Cancelled,

    Filled,

    Inactive;

  }

   /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private final InstrumentsCache instrumentsCache;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public IBApiHelper(InstrumentsCache instrumentsCache) {
    this.instrumentsCache = instrumentsCache;
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public Contract toContract(Instrument instrument) {
    Contract contract = new Contract();
    contract.m_symbol = instrument.getSymbol();
    contract.m_secType = instrument.getType().name();
    contract.m_currency = instrument.getCurrency();
    contract.m_primaryExch = instrument.getPreferredExchange();
    contract.m_conId = 0;
    return contract;
  }

  public Contract toContract(OrderPending order) {
    Contract contract = new Contract();
    contract.m_conId = 0;
    int instrumentId = order.getInstrumentId();
    Instrument instrument = instrumentsCache.getInstrument(instrumentId);
    if (instrument == null) {
      logger.error("Unable to create contract using invalid instrument " + instrumentId);
      return null;
    }
    contract.m_symbol = instrument.getSymbol();
    contract.m_secType = instrument.getType().name();
    contract.m_currency = Instrument.US_CURRENCY;
    contract.m_exchange = instrument.getPreferredExchange();
    return contract;
  }

  public Order toOrder(OrderPending orderPending, int clientId) {
    Order order = new Order();
    order.m_clientId = clientId;
    order.m_action = orderPending.getOrderSide().name();
    order.m_orderType = orderPending.getOrderType().name();
    order.m_totalQuantity = orderPending.getQuantity();
    return order;
  }


}
