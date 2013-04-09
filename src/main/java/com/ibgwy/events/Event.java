package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public abstract class Event {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/



  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  protected int id;

  protected int sourceId;

  private final short eventType;

    
  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/

  public Event(short eventType) {
    id = -1;
    this.eventType = eventType;
  }
    
    
  /*--------------------------------------------
  |              M E T H O D S                |
  ============================================*/

  public int getSourceId() {
    return sourceId;
  }

  public void setSourceId(int sourceId) {
    this.sourceId = sourceId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public short getEventType() {
    return eventType;
  }

}
