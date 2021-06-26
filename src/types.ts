export enum WXScene {
  Session = 0 /**< 聊天界面    */,
  Timeline = 1 /**< 朋友圈     */,
  Favorite = 2 /**< 收藏       */,
  SpecifiedSession = 3 /**< 指定联系人  */,
}

export enum WXMiniProgramType {
  Release = 0, //**< 正式版  */
  Test = 1, //**< 开发版  */
  Preview = 2, //**< 体验版  */
}

export declare type ImageSource = {
  uri: string;
  scale?: number;
  height?: number;
  width?: number;
};
