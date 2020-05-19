const db = wx.cloud.database();
const products = db.collection('products')
const banners = db.collection('banners')
const menu = db.collection('menu')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    products: [],
    banners: [],
    menu: [],
    searchvalue: ''
  },

  onChange(event) {
    console.log(event.detail)
    // event.detail 的值为当前选中项的索引
    this.setData({ 
      active: event.detail
    });
    // Tabbar 切换
    switch(event.detail) {
      case 1:
        wx.navigateTo({
          url: '../cart/cart',
        });
        break;
      case 2:
        wx.navigateTo({
          url: 'url',
        });
        break;
      case 3:
        wx.navigateTo({
          url: '../me/me',
        });
        break;
    }

  },

  onSearch() {
    console.log(this.data.searchvalue)
  },

  onClick() {
    console.log(this.data.searchvalue)
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
    })
    // 菜单栏
    menu.get().then(res=>{
      console.log(res.data)
      this.setData({
        menu: res.data
      })
    })
    // 产品列举
    products.get().then(res => {
      console.log(res.data)
      this.setData({
        products: res.data
      })
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})