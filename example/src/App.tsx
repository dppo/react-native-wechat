import * as React from 'react';

import { StyleSheet, View, Button, Image } from 'react-native';
import Wechat, { WXMiniProgramType, WXScene } from 'react-native-wechat';

const mp3Url =
  'http://m10.music.126.net/20210626175510/6752163190a54b8fd829b4700ec999da/ymusic/c494/8314/4f82/0bc14eee5027c81fb10df298c67f9e8e.mp3';

export default function App() {
  React.useEffect(() => {
    Wechat.registerApp('wx1cb034e9bd598a2b', 'https://www.wow-lemon.com/pm/');
  }, []);

  return (
    <View style={styles.container}>
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
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180520%2F0473e00bdfd2476fbe0c228a45a1652c.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627290818&t=bfd62c8174ecd0b629d2bbd475264e8c',
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
            Image.resolveAssetSource(require('./assets/icon_1024.png')),
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
            Image.resolveAssetSource(require('./assets/icon_1024.png')),
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
            Image.resolveAssetSource(require('./assets/icon_1024.png')),
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
            Image.resolveAssetSource(require('./assets/icon_1024.png')),
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
