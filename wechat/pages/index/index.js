// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      navList:[],
    productList:[
      {
        "id" : 1,
        "name":"华为Mate 30",
        "src" : "/images/temp/cate1.jpg",
        "price" : 4099  
      },
      {
        "id": 2,
        "name": "华为Mate 30",
        "src": "/images/temp/cate2.jpg",
        "price": 4099  
      },
      {
        "id": 3,
        "name": "华为Mate 30",
        "src": "/images/temp/cate3.jpg",
        "price": 4099  
      },
      {
        "id": 4,
        "name": "华为Mate 30",
        "src": "/images/temp/cate4.jpg",
        "price": 4099  
      },
      {
        "id": 5,
        "name": "华为Mate 30",
        "src": "/images/temp/cate5.jpg",
        "price": 4099  
      },
      {
        "id": 6,
        "name": "华为Mate 30",
        "src": "/images/temp/cate6.jpg",
        "price": 4099  
      },
      {
        "id": 7,
        "name": "华为Mate 30",
        "src": "/images/temp/cate7.jpg",
        "price": 4099
      },
      {
        "id": 8,
        "name": "华为Mate 30",
        "src": "/images/temp/cate8.jpg",
        "price": 4099
      },
      {
        "id": 9,
        "name": "华为Mate 30",
        "src": "/images/temp/cate9.jpg",
        "price": 4099
      },
      {
        "id": 10,
        "name": "华为Mate 30",
        "src": "/images/temp/cate10.jpg",
        "price": 4099,
        checked:true
      },]
  },
  getNavList:function(){
    let that= this;
    wx.request({
      url: 'http://www.hengyishun.cn/login/navlist',
      success(res){
         that.setData({
           navList:res.data
         })
      }
    })

  },

  getProductList:function(){
    let that = this;
    wx.request({
      url: 'http://localhost:8080/product/hello',
      success(res) {
        that.setData({
          productList: res.data
        })
      }
    })

  },
 
 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.getNavList();
      // this.getProductList();
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