import * as wxdb from "../../request/wxdbservice.js";
Page({
  data: {
    products: [],
    banners: [],
    menu: [],
    floor: [],
  },

  onLoad: function (options) {
    // 轮播图
    wxdb.getCollection("banners").then((res) => {
      console.log(res.data);
      this.setData({
        banners: res.data,
      });
    });
    // 导航栏
    wxdb.getCollection("menu").then((res) => {
      console.log(res.data);
      this.setData({
        menu: res.data,
      });
    });
    // 楼层
    wxdb.getCollection("floor").then((res) => {
      console.log("floor: " + res.data);
      this.setData({
        floor: res.data,
      });
    });
  },
});
