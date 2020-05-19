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
    banners: [],
    menu: [],
    navList: [],
    productList: [
      {
        "id": 1,
        "name": "华为Mate 30",
        "src": "/images/temp/cate1.jpg",
        "price": 4099
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
        checked: true
      },]
  },
  getNavList: function () {
    let that = this;
    wx.request({
      url: 'http://www.hengyishun.cn/login/navlist',
      success(res) {
        that.setData({
          navList: res.data
        })
      }
    })

  },

  getProductList: function () {
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
   // this.getNavList();
    //this.getProductList();
  },

})