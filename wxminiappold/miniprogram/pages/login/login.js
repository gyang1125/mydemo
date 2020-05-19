const db = wx.cloud.database();
const users = db.collection("users")
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showMsg:false,
    emptyMsg:false
  },
  login(event){
    console.log(event.detail.value.username)
    let username = event.detail.value.username;
    let pwd = event.detail.value.pwd;
    if(username.length == 0 || pwd.length == 0){
      // wx.showToast({
      //   title: '用户名或密码不能为空',
      // })
      this.setData({
        emptyMsg:true
      })
      return
    }
    db.collection('users').where({
      name:username,
      pwd:pwd
    }).get().then(res=>{
      console.log(res.data)
      if(res.data[0] == null) {
        this.setData({
          showMsg:true
        })
        return
      }else {
        this.setData({
          showMsg:false
        })
        app.user = res.data[0]; // gloabl variable
        console.log(app.user.name)
        wx.redirectTo({
          url: '../index/index',
        })
      }
    })
  },

  hideErrMsg(event){
    if(event.detail.value.length > 0){
      this.setData({
        emptyMsg:false
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.cloud.callFunction({
    //   name:'addUser',
    //   data:{
    //     name:'Guilin',
    //     pwd:'1234'
    //   }
    // }).then(res=>{
    //   console.log(res);
    // })
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