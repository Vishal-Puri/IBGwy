package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class OrderExecuted extends Event {


  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private double price;

  private int filled;

  private int remaining;

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  public OrderExecuted() {
    super(EventTypes.Orders.EXECUTED);
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public int getFilled() {
    return filled;
  }

  public void setFilled(int filled) {
    this.filled = filled;
  }

  public int getRemaining() {
    return remaining;
  }

  public void setRemaining(int remaining) {
    this.remaining = remaining;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("OrderExecuted");
    sb.append("{id=").append(id);
    sb.append(", clientId=").append(getSourceId());
    sb.append(", filled=").append(getFilled());
    sb.append(", remaining=").append(remaining);
    sb.append('}');
    return sb.toString();
  }
}
