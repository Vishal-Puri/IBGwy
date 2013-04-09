package com.ibgwy;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.ib.client.*;
import com.ibgwy.events.OrderPending;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Add JavaDoc!
 */
public class OutboundImpl implements AdminCommands, ConnectionCommands, Outbound {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private Logger logger = LoggerFactory.getLogger(OutboundImpl.class);

  private EClientSocket socket;

  private AnyWrapper api;

  private final String hostAddress;

  private final int port;

  private final int clientId;

  private boolean initialized;

  private boolean connected;

  private int numIds = 1;

  private IBApiHelper ibApiHelper;

  private InstrumentsCache instrumentsCache;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public OutboundImpl(AnyWrapper api, String hostAddress, int port, int clientId, IBApiHelper ibApiHelper,
                      InstrumentsCache instrumentsCache) {

    Preconditions.checkNotNull(api);
    Preconditions.checkArgument(!Strings.isNullOrEmpty(hostAddress));
    Preconditions.checkNotNull(ibApiHelper);
    this.api = api;
    this.hostAddress = hostAddress;
    this.port = port;
    this.clientId = clientId;
    this.ibApiHelper = ibApiHelper;
    this.instrumentsCache = instrumentsCache;
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  // ~ AdminCommands Methods ==================

  @Override
  public void startup() {
    if (!initialized) {
      logger.info("Initializing gateway");
      socket = new EClientSocket(api);
      socket.reqIds(numIds);
      initialized = true;
    } else {
    }
  }

  @Override
  public void shutdown() {
    if (initialized) {
      logger.info("Shutting down gateway");
      socket = null;
      initialized = false;
    }
  }

  protected void setSocket(EClientSocket socket) {
    this.socket = socket;
  }

  // ~ ConnectionCommands Methods ==============

  @Override
  public void connect() {
    if (!initialized) {
      startup();
    } else {
      if (!connected) {
        try {
          socket.eConnect(hostAddress, port, clientId);
          connected = socket.isConnected();
        } catch (Throwable t) {
          logger.error("Error connecting to :" + hostAddress + ":" + port);
        }
      } else {
        logger.info("Gateway already connected to " + hostAddress + ":" + port);
      }
    }
  }

  @Override
  public void disconnect() {
    if (connected) {
      logger.info("Disconnecting Gateway");
      socket.eDisconnect();
      String msg = EWrapperMsgGenerator.connectionClosed();
      logger.info(msg);
      connected = false;
    } else {
      logger.info("Gateway not connected");
    }
  }

  // ~ EClientSocket methods ==================

  /**
   * Asynchronous market data request call that calls-back on <code>IBApi</code>#tickPrice()
   *
   * @param mktDataReq
   */
  @Override
  public void reqMktData(InstrumentUpdateRequest mktDataReq) {
    try {
      Contract contract = ibApiHelper.toContract(instrumentsCache.getInstrument(mktDataReq.getInstrumentId()));
      socket.reqMktData(mktDataReq.getId(), contract, " ", mktDataReq.isSnapshot());

    } catch (Throwable t) {
      // TODO: Cancel event!
      logger.error("Error requesting market data request " + mktDataReq);
    }
  }

  @Override
  public void cancelHistoricalData(int tickerId) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void reqHistoricalData(int tickerId, Contract contract, String endDateTime, String durationStr, String
      barSizeSetting, String whatToShow, int useRTH, int formatDate) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void cancelMktData(int tickerId) {
    socket.cancelMktData(tickerId);
  }

  @Override
  public void placeOrder(OrderPending orderEvent) {
    Contract contract = ibApiHelper.toContract(orderEvent);
    Order order = ibApiHelper.toOrder(orderEvent, clientId);
    try {
      socket.placeOrder(orderEvent.getOrderId(), contract, order);
    } catch (Throwable t) {
      logger.error("Error placing order " + orderEvent);
    }
  }

  @Override
  public void cancelOrder(int id) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}