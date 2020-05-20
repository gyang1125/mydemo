const db = wx.cloud.database();
const products = db.collection("products");
const banners = db.collection("banners");
const menu = db.collection("menu");
const floor = db.collection("floor");

Page({
  data: {
    products: [],
    banners: [],
    menu: [],
    floor: [],
  },

  onLoad: function (options) {
    // 轮播图
    banners.get().then((res) => {
      console.log(res.data);
      this.setData({
        banners: res.data,
      });
    });
    // 导航栏
    menu.get().then((res) => {
      console.log(res.data);
      this.setData({
        menu: res.data,
      });
    });
    // 楼层
    floor.get().then((res) => {
      console.log('floor: ' + res.data);
      this.setData({
        floor: res.data,
      });
    });
  },
  
});
