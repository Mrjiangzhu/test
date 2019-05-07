/**
 */
require.config({
  baseUrl: "../../xiou",
  paths: {
    text: ["../lib/require/text"],
    html: ["../lib/require/html"],
    link: ["../lib/require/link"],
    index: ["index/v3/index"], //首页模块
    topbar: ["index/v3/topbar"], //顶部工具兰
    leftmenu: ["index/v3/leftmenu"], //左边菜单
    tabbar: ["index/v3/tabbar"], //tab工具栏
    tabcontent: ["index/v3/tabcontent"], //tab的iframe内容
    leftFloatPanel: ["index/v3/leftFloatPanel"], //左边浮动菜单
    login: ["index/v3/login"], //登录
    museAlert: ["index/v3/museAlert"], //登录
    toolkit: ["index/v3/toolkit"], //工具函数
    toast: ["../lib/muse-ui/muse-ui-toast"], //toast
    message: ["../lib/muse-ui/muse-ui-message"], //message
    vue: ["../lib/vue.min"], //vue
  },
  map: {
    "*": {
      css: "../lib/require/css.min"
    }
  }
});

require([
  "css!index.css",
  "toolkit",
  "topbar",
  "leftmenu",
  "tabbar",
  "tabcontent",
  "leftFloatPanel",
  "museAlert",
  "login"
], function () {
  require(["index"]);
});
