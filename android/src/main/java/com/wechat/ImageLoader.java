package com.wechat;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class ImageLoader {
  private static final String FILE_SCHEME = "file";

  public static Drawable loadImage(Application application, String iconSource) {
    if (isApkInDebug(application)) {
      return JsDevImageLoader.loadIcon(application, iconSource);
    } else {
      Uri uri = Uri.parse(iconSource);
      if (isLocalFile(uri)) {
        return loadFile(application, uri);
      } else {
        return loadResource(application, iconSource);
      }
    }
  }

  private static boolean isLocalFile(Uri uri) {
    return FILE_SCHEME.equals(uri.getScheme());
  }

  private static Drawable loadFile(Application application, Uri uri) {
    Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
    return new BitmapDrawable(application.getResources(), bitmap);
  }

  private static Drawable loadResource(Application application, String iconSource) {
    return ResourceDrawableIdHelper.instance.getResourceDrawable(application, iconSource);
  }

  /**
   * 判断当前应用是否是debug状态
   */
  public static boolean isApkInDebug(Context context) {
    try {
      ApplicationInfo info = context.getApplicationInfo();
      return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    } catch (Exception e) {
      return false;
    }
  }
}
