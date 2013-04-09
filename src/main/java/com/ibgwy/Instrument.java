package com.ibgwy;

import com.google.common.base.Strings;

/**
 * TODO: Add JavaDoc!
 */
public class Instrument {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  public static final String US_CURRENCY = "USD";

  public enum InstrumentType {

    /**
     * Stock
     */
    STK,

    /**
     * Index
     */
    IND;

  }

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private final int id;

  private final InstrumentType type;

  private final String symbol;

  private final String preferredExchange;

  private String currency = US_CURRENCY;

  private double price;

  private int quantity;
    

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public Instrument(int id, InstrumentType type, String symbol, String preferredExchange, String currency) {
    this.id = id;
    this.type = type;
    this.symbol = symbol;
    this.preferredExchange = preferredExchange;
    if (!Strings.isNullOrEmpty(currency)) {
      // TODO: Check Valid Currency!
      this.currency = currency;
    }
  }

  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public int getId() {
    return id;
  }

  public InstrumentType getType() {
    return type;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getPreferredExchange() {
    return preferredExchange;
  }

  public String getCurrency() {
    return currency;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Instrument that = (Instrument) o;

    if (id != that.id) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public String toString() {
    return "Instrument{" +
        "id=" + id +
        ", type=" + type +
        ", symbol='" + symbol + '\'' +
        ", preferredExchange='" + preferredExchange + '\'' +
        ", currency='" + currency + '\'' +
        '}';
  }
}
