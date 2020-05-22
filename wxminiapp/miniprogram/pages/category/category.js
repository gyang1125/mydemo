const db = wx.cloud.database();
const category = db.collection("category");

Page({
  data: {
    leftMenuList: [],
    rightContent: [],
    currentIndex: 0,
    scrollTop: 0,
  },
  Cates: [],

  onLoad: function (options) {
    console.log("分类加载");

    // 设置缓存
    const Cates = wx.getStorageSync("cates");
    if (!Cates) {
      console.log("第一次取值");
      this.getCategory();
    } else {
      if (Date.now() - Cates.time > 1000 * 10) {
        console.log("超过十秒，再次取新值");
        this.getCategory();
      } else {
        console.log("从缓存中取旧数据");
        this.Cates = Cates.data;
        let leftMenuList = this.Cates.map((v) => v.cat_name);
        let rightContent = this.Cates[0].regions;
        this.setData({
          leftMenuList,
          rightContent,
        });
      }
    }
  },

  getCategory() {
    category.get().then((res) => {
      console.log(res.data);
      this.Cates = res.data;
      // 存入缓存
      wx.setStorageSync("cates", { time: Date.now(), data: this.Cates });
      let leftMenuList = this.Cates.map((v) => v.cat_name);
      let rightContent = this.Cates[0].regions;
      this.setData({
        leftMenuList,
        rightContent,
      });
    });
  },

  handleItemTap(e) {
    console.log(e);
    const { index } = e.currentTarget.dataset;
    let rightContent = this.Cates[index].regions;
    this.setData({
      currentIndex: index,
      rightContent,
      // 重新设置scroll标签的顶部距离
      scrollTop: 0,
    });
  },
});
