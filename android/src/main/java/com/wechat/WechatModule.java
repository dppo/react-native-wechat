package com.wechat;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.ByteArrayOutputStream;
import java.net.URL;

@ReactModule(name = WechatModule.NAME)
public class WechatModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Wechat";
  private IWXAPI api;
  private ReactApplicationContext reactContext;

  public WechatModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void registerApp(String appId, String universalLink) {
    WXSingleton.getInstance().registerWXApp(this.reactContext, appId);
  }

  @ReactMethod
  public void checkUniversalLinkReady() {
  }

  @ReactMethod
  public void sendText(String text, int scene, Promise promise) {
    WXTextObject textObj = new WXTextObject();
    textObj.text = text;

    WXMediaMessage msg = new WXMediaMessage();
    msg.mediaObject = textObj;
    msg.description = text;

    SendMessageToWX.Req req = new SendMessageToWX.Req();
    req.transaction = String.valueOf(System.currentTimeMillis());
    req.message = msg;
    req.scene = scene;

    WXSingleton.getInstance().api.sendReq(req);
  }

  @ReactMethod
  public void sendImage(String url, int scene, Promise promise) {
    try {
      Bitmap bmp = null;
      bmp = BitmapFactory.decodeStream(new URL(url).openStream());

      WXImageObject imgObj = new WXImageObject(bmp);
      WXMediaMessage msg = new WXMediaMessage();
      msg.mediaObject = imgObj;

      Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
      bmp.recycle();
      msg.thumbData = Bitmap2Bytes(thumbBmp);

      SendMessageToWX.Req req = new SendMessageToWX.Req();
      req.transaction = String.valueOf(System.currentTimeMillis());
      req.message = msg;
      req.scene = scene;

      WXSingleton.getInstance().api.sendReq(req);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @ReactMethod
  public void sendMusic(String title, String description, String musicUrl, String musicLowBandUrl, String musicDataUrl, String musicLowBandDataUrl, ReadableMap thumbImage, int scene, Promise promise) {
    WXMusicObject music = new WXMusicObject();
    music.musicUrl = musicUrl;

    WXMediaMessage msg = new WXMediaMessage();
    msg.mediaObject = music;
    msg.title = title;
    msg.description = description;
    Bitmap bitmap = getBitmap(getCurrentActivity().getApplication(), thumbImage);
    if (null != bitmap) {
      msg.setThumbImage(bitmap);
    }

    SendMessageToWX.Req req = new SendMessageToWX.Req();
    req.transaction = String.valueOf(System.currentTimeMillis());
    req.message = msg;
    req.scene = scene;

    WXSingleton.getInstance().api.sendReq(req);
  }

  @ReactMethod
  public void sendVideo(String title, String description, String videoUrl, String videoLowBandUrl, ReadableMap thumbImage, int scene, Promise promise) {
    WXVideoObject video = new WXVideoObject();
    video.videoUrl = videoUrl;

    WXMediaMessage msg = new WXMediaMessage();
    msg.mediaObject = video;
    msg.title = title;
    msg.description = description;
    Bitmap bitmap = getBitmap(getCurrentActivity().getApplication(), thumbImage);
    if (null != bitmap) {
      msg.setThumbImage(bitmap);
    }

    SendMessageToWX.Req req = new SendMessageToWX.Req();
    req.transaction = String.valueOf(System.currentTimeMillis());
    req.message = msg;
    req.scene = scene;

    WXSingleton.getInstance().api.sendReq(req);
  }

  @ReactMethod
  public void sendWebpage(String title, String description, String webpageUrl, ReadableMap thumbImage, int scene, Promise promise) {
    WXWebpageObject webpage = new WXWebpageObject();
    webpage.webpageUrl = webpageUrl;

    WXMediaMessage msg = new WXMediaMessage();
    msg.mediaObject = webpage;
    msg.title = title;
    msg.description = description;
    Bitmap bitmap = getBitmap(getCurrentActivity().getApplication(), thumbImage);
    if (null != bitmap) {
      msg.setThumbImage(bitmap);
    }

    SendMessageToWX.Req req = new SendMessageToWX.Req();
    req.transaction = String.valueOf(System.currentTimeMillis());
    req.message = msg;
    req.scene = scene;

    WXSingleton.getInstance().api.sendReq(req);
  }

  @ReactMethod
  public void sendMiniProgram(String title, String description, String webpageUrl, String userName, String path, ReadableMap hdImage, boolean shareTicket, int miniprogramType, int scene, Promise promise) {
    WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
    miniProgramObj.webpageUrl = webpageUrl;
    miniProgramObj.miniprogramType = miniprogramType;
    miniProgramObj.userName = userName;
    miniProgramObj.path = path;

    WXMediaMessage msg = new WXMediaMessage();
    msg.mediaObject = miniProgramObj;
    msg.title = title;
    msg.description = description;
    Bitmap bitmap = getBitmap(getCurrentActivity().getApplication(), hdImage);
    if (null != bitmap) {
      msg.setThumbImage(bitmap);
    }

    SendMessageToWX.Req req = new SendMessageToWX.Req();
    req.transaction = String.valueOf(System.currentTimeMillis());
    req.message = msg;
    req.scene = scene;

    WXSingleton.getInstance().api.sendReq(req);
  }

  public static Bitmap getBitmap(Application application, ReadableMap image) {
    Bitmap bitmap = null;
    Drawable drawable = getRNImage(application, image);
    if (null != drawable) {
      BitmapDrawable bd = (BitmapDrawable) drawable;
      bitmap = bd.getBitmap();
    }
    return bitmap;
  }

  public static Drawable getRNImage(Application application, ReadableMap image) {
    Drawable icon = null;
    try {
      icon = ImageLoader.loadImage(application, image.getString("uri"));
    } catch (Exception ignored) {
    }
    return icon;
  }

  public static byte[] Bitmap2Bytes(Bitmap bm) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
    return baos.toByteArray();
  }
}
