// initialize the cloud db
const db = wx.cloud.database();
const products = db.collection("products");

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  onSubmit:function(event) {
    console.log(event.detail.value);
    products.add({
      data:{
        name: event.detail.value.name,
        price: event.detail.value.price,
        desc: event.detail.value.desc
      }
    }).then(res=>{
      console.log(res);
    })
  }
})