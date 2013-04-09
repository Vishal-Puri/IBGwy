package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class OrderAccepted extends Event {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  private int quantity;

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public OrderAccepted() {
    super(EventTypes.Orders.ACCEPTED);
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("OrderAccepted");
    sb.append("{id=").append(id);
    sb.append(" clientId=").append(getSourceId());
    sb.append(", quantity=").append(getQuantity());
    sb.append('}');
    return sb.toString();
  }
}
