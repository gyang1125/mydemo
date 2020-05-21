const db = wx.cloud.database();
const category = db.collection("category");

Page({
  data: {
    leftMenuList: [],
    rightContent: [],
    currentIndex: 0,
  },
  Cates: [],

  onLoad: function (options) {
    category.get().then((res) => {
      console.log(res.data);
      this.Cates = res.data;
      let leftMenuList = this.Cates.map(v=>v.cat_name);
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
      rightContent
    })
  },
});
