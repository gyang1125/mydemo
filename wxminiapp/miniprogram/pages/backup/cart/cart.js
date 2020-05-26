//Page Object
import {
  getSetting,
  chooseAddress,
  openSetting,
} from "../../../utils/asycWx.js";
import regeneratorRuntime from "../../../lib/runtime/runtime";

Page({
  data: {
    address: {},
  },

  async handleAddress() {
    try {
      // 1 获取 权限状态
      const res1 = await getSetting();
      const scopeAddress = res1.authSetting["scope.address"];
      //2 判断 权限状态 false/true/undefined
      if (scopeAddress === false) {
        // 3 先诱导用户打开授权页面
        await openSetting();
      }
      // 4 调用获取收货地址的 api
      let address = await chooseAddress();
      console.log(address);
      address.all =
        address.provinceName +
        address.cityName +
        address.countyName +
        address.detailInfo;
      // 5 获取到的地址放入缓存
      wx.setStorageSync("address", address);
      console.log(res2);
    } catch (error) {
      console.log(error);
    }
  },

  //options(Object)
  onLoad: function (options) {},
  onShow: function () {
    // 1 获取缓存中的收货信息
    const address = wx.getStorageSync("address");
    // 2 给data赋值
    this.setData({
      address,
    });
  },
});
