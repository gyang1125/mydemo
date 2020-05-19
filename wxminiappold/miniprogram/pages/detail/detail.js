const db = wx.cloud.database();
const products = db.collection("products")
const carts = db.collection("carts")
const orders = db.collection("orders")
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    product: {}
  },
  /**点击客户或者购物车 */
  onClickIcon() {
    console.log('点击图标')
  },
  /**点击加入购物车或者立即购买 */
  onClickButton() {
    console.log('点击按钮')
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.id == null) {
      return;
    }
    products.doc(options.id).get().then(res => {
      console.log(res.data);
      let product = res.data
      this.setData({
        product: res.data
      })
    })
  },
  /**添加商品到购物车 */
  addCart() {
    carts.add({
      data: {
        product: this.data.product
      }
    })
    wx.showModal({
      title: '成功提示',
      content: '添加商品到购物车成功',
      success: (res) => {
        if (res.confirm) {
          app.carNumber++;
        }
      }
    })

  },
  /**付款 */
  prePay: function () {
    let cart = []
    this.data.product.countOut = 1
    cart.push(this.data.product)
    //添加一条主信息
    orders.add({
      data:{
        userId: app.user.phoneNumber,
        details: cart,
        status: 0,//支付状态
        orderCode: this.js_data_time(),
        orderTime: this.orderTime()
      }
    }).then(res=>{
      console.log('id' + res._id)
      wx.navigateTo({
        url: '../pay/pay?id=' + res._id,
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