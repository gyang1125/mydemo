// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    productId: ""
  },
  /**添加到购物车 */
  addCar() {
    //将数据存储到本地的同步方法
    wx.setStorageSync("id", this.data.productId);
    //模态窗口友好提示
    wx.showModal({
      title: '购物车提示',
      content: '产品添加到购物车成功',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      productId: options.id,
    })
  }
})