package com.ibgwy.listeners;

import com.ib.client.Contract;

public interface AccountUpdateListener {

  public void updateAccountValue(String key, String value, String currency, String accountName);

  public void updatePortfolio(Contract contract, int position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName);

  public void updateAccountTime(String timeStamp);

  public void accountDownloadEnd(String accountName);


}
