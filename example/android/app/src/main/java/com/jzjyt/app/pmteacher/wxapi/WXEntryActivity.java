package com.jzjyt.app.pmteacher.wxapi;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wechat.WXSingleton;

import java.util.List;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    handleIntent();
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    setIntent(intent);
    handleIntent();
  }

  void handleIntent() {
    if (WXSingleton.getInstance().api == null) {
      back();
    } else {
      WXSingleton.getInstance().api.handleIntent(getIntent(), this);
    }
  }

  @Override
  public void onReq(BaseReq baseReq) {
    finish();
  }

  @Override
  public void onResp(BaseResp baseResp) {
    finish();
  }

  public void back() {
    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    assert manager != null;
    List<ActivityManager.RunningTaskInfo> infos = manager.getRunningTasks(1);
    ActivityManager.RunningTaskInfo info = infos.get(0);

    Intent intent = NavUtils.getParentActivityIntent(this);
    if (info.numActivities > 1) {
      NavUtils.navigateUpFromSameTask(this);
    } else {
      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          TaskStackBuilder.create(getApplicationContext()).addNextIntentWithParentStack(intent).startActivities();
        }
      }, 300);
    }
  }
}
