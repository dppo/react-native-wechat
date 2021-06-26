iOS 配置

1、在 Xcode 中，选择你的工程设置项，选中“TARGETS”一栏，在“info”标签栏的“URL type“添加“URL scheme”为你所注册的应用程序 id
identifier weixin

2、在 Xcode 中，选择你的工程设置项，选中“TARGETS”一栏，在 “info”标签栏的“LSApplicationQueriesSchemes“添加 weixin 和 weixinULAPI

3、AppDelegate 文件中添加

```
#import <react-native-wechat/WechatManager.h>

- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
{
  return [WXApi handleOpenURL:url delegate:[WechatManager sharedManager]];
}

- (BOOL)application:(UIApplication *)application continueUserActivity:(NSUserActivity *)userActivity restorationHandler:(void (^)(NSArray<id<UIUserActivityRestoring>> * _Nullable))restorationHandler
{
  return [WXApi handleOpenUniversalLink:userActivity delegate:[WechatManager sharedManager]];
}
```


