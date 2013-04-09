package com.ibgwy.events;

/**
 * TODO: Add JavaDoc!
 * <p/>
 * User: Vishal Puri
 */
public abstract class EventTypes {


  public static final class Orders {
    public static final short ACCEPTED = 0;
    public static final short CANCELLED = 1;
    public static final short EXECUTED = 2;
    public static final short PENDING = 3;
  }

  public static final class Instrument {
    public static final short UPDATE = 4;
    public static final short UPDATE_REQUEST = 5;
  }


}
