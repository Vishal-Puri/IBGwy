package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class
    OrderPending extends Event {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  public static enum OrderType {

    MKT,

  }

  public static enum OrderSide {

    BUY,

    SELL,

    SSHORT;

  }

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  protected int parentId = -1;

  protected int orderId;

  protected long date;

  protected int instrumentId;

  protected OrderType orderType;

  protected OrderSide orderSide;

  protected int quantity;

  protected int clientId;

  protected double price;

  protected String symbol;
    
  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/


  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public OrderPending() {
    this(-1);
  }

  public OrderPending(int parentId) {
    super(EventTypes.Orders.PENDING);
    this.parentId = parentId;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

  public OrderSide getOrderSide() {
    return orderSide;
  }

  public void setOrderSide(OrderSide orderSide) {
    this.orderSide = orderSide;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  public int getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(int instrumentId) {
    this.instrumentId = instrumentId;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }


}
