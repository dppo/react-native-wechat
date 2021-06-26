//
//  WechatManager.h
//  react-native-wechat
//
//  Created by 高昇 on 2021/6/26.
//

#import <Foundation/Foundation.h>
#import <WechatOpenSDK/WXApi.h>

NS_ASSUME_NONNULL_BEGIN

@interface WechatManager : NSObject <WXApiDelegate>

+ (instancetype)sharedManager;

@end

NS_ASSUME_NONNULL_END
