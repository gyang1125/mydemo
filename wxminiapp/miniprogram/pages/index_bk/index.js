const db = wx.cloud.database();
const products = db.collection('products')
const banners = db.collection('banners')
const menu = db.collection('menu')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    products: [],
    banners: []
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 广告条幅
    banners.get().then(res => {
      console.log(res.data)
      this.setData({
        banners: res.data
      })
    });
    // 产品列举
    products.get().then(res => {
      console.log(res.data)
      this.setData({
        products: res.data
      })
    })

  },

})