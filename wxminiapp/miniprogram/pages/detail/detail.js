import * as wxdb from "../../request/wxdbservice.js";
const db = wx.cloud.database();
const products = db.collection("products");
import regeneratorRuntime from "../../lib/runtime/runtime";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    products: {},
    // 商品是否被收藏
    isCollect: false,
  },
  // 商品对象
  productsInfo: {},
  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function () {
    let pages = getCurrentPages();
    let currentPage = pages[pages.length - 1];
    let options = currentPage.options;
    const { pid } = options;
    this.getProductsDetail(pid);
    console.log("pid: " + pid);
  },
  // 获取商品详情数据
  getProductsDetail(pid) {
    products
      .where({
        pid: pid,
      })
      .get()
      .then((res) => {
        console.log(res.data);
        this.productsInfo = res.data[0];
        // 1 获取缓存中的商品收藏的数组
        let collect = wx.getStorageSync("collect") || [];
        // 2 判断当前商品是否被收藏
        let isCollect = collect.some((v) => v.pid === this.productsInfo.pid);
        this.setData({
          products: res.data[0],
          isCollect,
        });
      });
  },
  // 点击轮播图 放大预览
  handlePrevewImage(e) {
    // 1 先构造要预览的图片数组
    const urls = this.productsInfo.pics.map((v) => v.pics_mid);
    // 2 接收传递过来的图片url
    const current = e.currentTarget.dataset.url;
    wx.previewImage({
      current,
      urls,
    });
  },
  // 点击 加入购物车
  handleCartAdd() {
    // 1 获取缓存中的购物车 数组
    let cart = wx.getStorageSync("cart") || [];
    // 2 判断 商品对象是否存在于购物车数组中
    let index = cart.findIndex((v) => v.pid === this.productsInfo.pid);
    console.log("已经存在购物车");
    if (index === -1) {
      //3  不存在 第一次添加
      console.log("第一次添加");
      this.productsInfo.num = 1;
      this.productsInfo.checked = true;
      cart.push(this.productsInfo);
    } else {
      // 4 已经存在购物车数据 执行 num++
      cart[index].num++;
      console.log("再次添加");
    }
    // 5 把购物车重新添加回缓存中
    wx.setStorageSync("cart", cart);
    // 6 弹窗提示
    wx.showToast({
      title: "加入成功",
      icon: "success",
      // true 防止用户 手抖 疯狂点击按钮
      mask: true,
    });
  },
  // 点击 商品收藏图标
  handleCollect() {
    let isCollect = false;
    // 1 获取缓存中的商品收藏数组
    let collect = wx.getStorageSync("collect") || [];
    // 2 判断该商品是否被收藏过
    let index = collect.findIndex((v) => v.pid === this.productsInfo.pid);
    // 3 当index！=-1表示 已经收藏过
    if (index !== -1) {
      // 能找到 已经收藏过了  在数组中删除该商品
      collect.splice(index, 1);
      isCollect = false;
      wx.showToast({
        title: "取消成功",
        icon: "success",
        mask: true,
      });
    } else {
      // 没有收藏过
      collect.push(this.productsInfo);
      isCollect = true;
      wx.showToast({
        title: "收藏成功",
        icon: "success",
        mask: true,
      });
    }
    // 4 把数组存入到缓存中
    wx.setStorageSync("collect", collect);
    // 5 修改data中的属性  isCollect
    this.setData({
      isCollect,
    });
  },
});
