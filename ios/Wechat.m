#import "Wechat.h"
#import <WechatOpenSDK/WXApi.h>
#import <React/RCTConvert.h>

@implementation Wechat

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(registerApp,
                 registerAppWithAppId:(nonnull NSString *)appId
                 withUniversalLink:(nonnull NSString *)universalLink)
{
    [WXApi registerApp:appId universalLink:universalLink];
}

RCT_EXPORT_METHOD(checkUniversalLinkReady)
{
    [WXApi checkUniversalLinkReady:^(WXULCheckStep step, WXCheckULStepResult* result) {
        NSLog(@"%@, %u, %@, %@", @(step), result.success, result.errorInfo, result.suggestion);
    }];
}

RCT_REMAP_METHOD(sendText,
                 sendText:(nonnull NSString *)text
                 withScene:(nonnull NSNumber *)scene
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.bText = YES;
    req.text = text;
    req.scene = [scene intValue];
    [WXApi sendReq:req completion:^(BOOL success) {
        resolve([NSNumber numberWithBool:success]);
    }];
}

RCT_REMAP_METHOD(sendImage,
                 sendImageWithUrl:(nonnull NSString *)url
                 withScene:(nonnull NSNumber *)scene
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    WXImageObject *imageObject = [WXImageObject object];
    imageObject.imageData = [NSData dataWithContentsOfURL:[NSURL URLWithString:url]];
    
    WXMediaMessage *message = [WXMediaMessage message];
    message.mediaObject = imageObject;
    
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.bText = NO;
    req.message = message;
    req.scene = [scene intValue];
    [WXApi sendReq:req completion:^(BOOL success) {
        resolve([NSNumber numberWithBool:success]);
    }];
}

RCT_REMAP_METHOD(sendMusic,
                 sendMusicWithTitle:(nonnull NSString *)title
                 withDescription:(nonnull NSString *)description
                 withMusicUrl:(nonnull NSString *)musicUrl
                 withMusicLowBandUrl:(nonnull NSString *)musicLowBandUrl
                 withMusicDataUrl:(nonnull NSString *)musicDataUrl
                 withMusicLowBandDataUrl:(nonnull NSString *)musicLowBandDataUrl
                 withThumbImage:(id)thumbImage
                 withScene:(nonnull NSNumber *)scene
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    WXMusicObject *musicObject = [WXMusicObject object];
    musicObject.musicUrl = musicUrl;
    musicObject.musicLowBandUrl = musicLowBandUrl;
    musicObject.musicDataUrl = musicDataUrl;
    musicObject.musicLowBandDataUrl = musicLowBandDataUrl;
    
    WXMediaMessage *message = [WXMediaMessage message];
    message.title = title;
    message.description = description;
    [message setThumbImage:[RCTConvert UIImage:thumbImage]];
    message.mediaObject = musicObject;
    
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.bText = NO;
    req.message = message;
    req.scene = [scene intValue];
    [WXApi sendReq:req completion:^(BOOL success) {
        resolve([NSNumber numberWithBool:success]);
    }];
}

RCT_REMAP_METHOD(sendVideo,
                 sendVideoWithTitle:(nonnull NSString *)title
                 withDescription:(nonnull NSString *)description
                 withVideoUrl:(nonnull NSString *)videoUrl
                 withVideoLowBandUrl:(nonnull NSString *)videoLowBandUrl
                 withThumbImage:(id)thumbImage
                 withScene:(nonnull NSNumber *)scene
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    WXVideoObject *videoObject = [WXVideoObject object];
    videoObject.videoUrl = videoUrl;
    videoObject.videoLowBandUrl = videoLowBandUrl;
    
    WXMediaMessage *message = [WXMediaMessage message];
    message.title = title;
    message.description = description;
    [message setThumbImage:[RCTConvert UIImage:thumbImage]];
    message.mediaObject = videoObject;
    
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.bText = NO;
    req.message = message;
    req.scene = [scene intValue];
    [WXApi sendReq:req completion:^(BOOL success) {
        resolve([NSNumber numberWithBool:success]);
    }];
}

RCT_REMAP_METHOD(sendWebpage,
                 sendWebpageWithTitle:(nonnull NSString *)title
                 withDescription:(nonnull NSString *)description
                 withWebpageUrl:(nonnull NSString *)webpageUrl
                 withThumbImage:(id)thumbImage
                 withScene:(nonnull NSNumber *)scene
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    WXWebpageObject *webpageObject = [WXWebpageObject object];
    webpageObject.webpageUrl = webpageUrl;
    
    WXMediaMessage *message = [WXMediaMessage message];
    message.title = title;
    message.description = description;
    [message setThumbImage:[RCTConvert UIImage:thumbImage]];
    message.mediaObject = webpageObject;
    
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.bText = NO;
    req.message = message;
    req.scene = [scene intValue];
    [WXApi sendReq:req completion:^(BOOL success) {
        resolve([NSNumber numberWithBool:success]);
    }];
}

RCT_REMAP_METHOD(sendMiniProgram,
                 sendMiniProgramWithTitle:(nonnull NSString *)title
                 withDescription:(nonnull NSString *)description
                 withWebpageUrl:(nonnull NSString *)webpageUrl
                 withUserName:(nonnull NSString *)userName
                 withPath:(nonnull NSString *)path
                 withHdImage:(id)hdImage
                 withShareTicket:(BOOL)shareTicket
                 withMiniprogramType:(nonnull NSNumber *)miniprogramType
                 withScene:(nonnull NSNumber *)scene
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    WXMiniProgramObject *object = [WXMiniProgramObject object];
    object.webpageUrl = webpageUrl;
    object.userName = userName;
    object.path = path;
    object.hdImageData = UIImageJPEGRepresentation([RCTConvert UIImage:hdImage], 1);
    object.withShareTicket = shareTicket;
    object.miniProgramType = [miniprogramType intValue];
    
    WXMediaMessage *message = [WXMediaMessage message];
    message.title = title;
    message.description = description;
    message.thumbData = nil;
    
    message.mediaObject = object;
    SendMessageToWXReq *req = [[SendMessageToWXReq alloc] init];
    req.bText = NO;
    req.message = message;
    req.scene = [scene intValue];
    [WXApi sendReq:req completion:^(BOOL success) {
        resolve([NSNumber numberWithBool:success]);
    }];
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

@end
