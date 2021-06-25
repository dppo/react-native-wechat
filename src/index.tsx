import { NativeModules } from 'react-native';

type WechatType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Wechat } = NativeModules;

export default Wechat as WechatType;
