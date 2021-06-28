import { NativeModules } from 'react-native';
import type { WXScene, WXMiniProgramType, ImageSource } from './types';
export * from './types';

type WechatType = {
  registerApp(appId: string, universalLink: string): void;
  isWXAppInstalled(): Promise<boolean>;
  isWXAppSupportApi(): Promise<number>;
  getWXAppInstallUrl(): Promise<string>;
  getApiVersion(): Promise<string>;
  openWXApp(): Promise<boolean>;
  checkUniversalLinkReady(): void;
  sendText(text: string, scene: WXScene): Promise<boolean>;
  sendImage(url: string, scene: WXScene): Promise<boolean>;
  sendMusic(
    title: string,
    description: string,
    musicUrl: string,
    musicLowBandUrl: string,
    musicDataUrl: string,
    musicLowBandDataUrl: string,
    thumbImage: ImageSource,
    scene: WXScene
  ): Promise<boolean>;
  sendVideo(
    title: string,
    description: string,
    videoUrl: string,
    videoLowBandUrl: string,
    thumbImage: ImageSource,
    scene: WXScene
  ): Promise<boolean>;
  sendWebpage(
    title: string,
    description: string,
    webpageUrl: string,
    thumbImage: ImageSource,
    scene: WXScene
  ): Promise<boolean>;
  sendMiniProgram(
    title: string,
    description: string,
    webpageUrl: string,
    userName: string,
    path: string,
    hdImage: ImageSource,
    shareTicket: boolean,
    miniprogramType: WXMiniProgramType,
    scene: WXScene
  ): Promise<boolean>;
};

const { Wechat } = NativeModules;

export default Wechat as WechatType;
