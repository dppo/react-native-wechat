//
//  WechatManager.m
//  react-native-wechat
//
//  Created by 高昇 on 2021/6/26.
//

#import "WechatManager.h"

static WechatManager* _instance = nil;

@implementation WechatManager

+ (instancetype)sharedManager
{
  static dispatch_once_t onceToken ;
  dispatch_once(&onceToken, ^{
    _instance = [[self alloc] init] ;
  }) ;
  return _instance ;
}

- (void)onReq:(BaseReq *)req
{
    
}

- (void)onResp:(BaseResp *)resp
{
    
}

@end
