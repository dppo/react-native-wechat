import * as React from 'react';

import { StyleSheet, View, Button, Image, Platform } from 'react-native';
import Wechat, { WXMiniProgramType, WXScene } from '../../src';

const mp3Url =
  'http://m10.music.126.net/20210626175510/6752163190a54b8fd829b4700ec999da/ymusic/c494/8314/4f82/0bc14eee5027c81fb10df298c67f9e8e.mp3';

export default function App() {
  React.useEffect(() => {
    Wechat.registerApp(
      Platform.OS == 'ios' ? 'wx1cb034e9bd598a2b' : 'wxa04062f1a4fefd99',
      'https://www.wow-lemon.com/pm/'
    );
  }, []);

  return (
    <View style={styles.container}>
      <Button
        title="是否安装微信"
        onPress={() => {
          Wechat.isWXAppInstalled().then((res) => {
            console.warn('res = ', res);
          });
        }}
      />
      <Button
        title="微信支持API"
        onPress={() => {
          Wechat.isWXAppSupportApi().then((res) => {
            console.warn('res = ', res);
          });
        }}
      />
      <Button
        title="微信下载地址"
        onPress={() => {
          Wechat.getWXAppInstallUrl().then((res) => {
            console.warn('res = ', res);
          });
        }}
      />
      <Button
        title="微信API版本"
        onPress={() => {
          Wechat.getApiVersion().then((res) => {
            console.warn('res = ', res);
          });
        }}
      />
      <Button
        title="打开微信"
        onPress={() => {
          Wechat.openWXApp().then((res) => {
            console.warn('res = ', res);
          });
        }}
      />
      <Button
        title="调用自检函数"
        onPress={() => {
          Wechat.checkUniversalLinkReady();
        }}
      />
      <Button
        title="分享文本"
        onPress={() => {
          Wechat.sendText('这是测试文本', WXScene.Session).then((success) => {
            console.warn('success = ', success);
          });
        }}
      />
      <Button
        title="分享图片"
        onPress={() => {
          Wechat.sendImage(
            'https://tenfei03.cfp.cn/creative/vcg/veer/1600water/veer-153091007.jpg',
            WXScene.Session
          ).then((success) => {
            console.warn('success = ', success);
          });
        }}
      />
      <Button
        title="分享音乐"
        onPress={() => {
          Wechat.sendMusic(
            '这是标题',
            '这是描述',
            mp3Url,
            mp3Url,
            mp3Url,
            mp3Url,
            Image.resolveAssetSource(require('./assets/banner01.png')),
            WXScene.Session
          ).then((success) => {
            console.warn('success = ', success);
          });
        }}
      />
      <Button
        title="分享视频"
        onPress={() => {
          Wechat.sendVideo(
            '这是标题',
            '这是描述',
            mp3Url,
            mp3Url,
            Image.resolveAssetSource(require('./assets/banner01.png')),
            WXScene.Session
          );
        }}
      />
      <Button
        title="分享网页"
        onPress={() => {
          Wechat.sendWebpage(
            '这是标题',
            '这是描述',
            'https://www.baidu.com',
            Image.resolveAssetSource(require('./assets/banner01.png')),
            WXScene.Session
          );
        }}
      />
      <Button
        title="分享小程序"
        onPress={() => {
          Wechat.sendMiniProgram(
            '小程序标题',
            '小程序描述',
            'https://www.baidu.com',
            'gh_92193f646457',
            '',
            Image.resolveAssetSource(require('./assets/banner01.png')),
            true,
            WXMiniProgramType.Preview,
            WXScene.Session
          ).then((success) => {
            console.warn('success = ', success);
          });
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
