package com.ibgwy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public class DefaultPriceImprovementHandler implements PriceImprovementHandler {

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/


  private Logger logger = LoggerFactory.getLogger(DefaultPriceImprovementHandler.class);

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public DefaultPriceImprovementHandler() {

  }
    
    
  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  //TODO: Price improvement logic goes here!
  @Override
  public double priceImprovedBuy(double price) {
    if (logger.isDebugEnabled()) {
      logger.debug("Starting price improvement utility function");
    }
    return price;
  }

  //TODO: Price improvement logic goes here!
  @Override
  public double priceImprovedSell(double price) {
    if (logger.isDebugEnabled()) {
      logger.debug("Starting price improvement utility function");
    }
    return price;
  }
}
