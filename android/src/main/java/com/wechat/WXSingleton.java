package com.wechat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXSingleton {
  public IWXAPI api;

  private WXSingleton() {
  }

  private static final WXSingleton single = new WXSingleton();

  public static WXSingleton getInstance() {
    return single;
  }

  public void registerWXApp(Context context, String appId) {
    api = WXAPIFactory.createWXAPI(context, appId, true);

    context.registerReceiver(new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        api.registerApp(appId);
      }
    }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
  }
}
